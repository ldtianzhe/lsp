# lsp

## 环境部署

### 准备工作

```
JDK >=1.8 推荐1.8版本
MySQL >=5.7 推荐5.7版本
Redis >=3.0
Maven >=3.0
Node >=10.0
```

### 运行系统

#### 后端运行

1. 下载项目文件，打开IDEA -->OPEN，然后选择 lsp, 点击 ok。初次加载会自动加载Maven依赖包会比较慢(可通过设置如下镜像)

   ```
   <mirror>
         <id>alimaven</id>
         <name>aliyun maven</name>
         <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
         <mirrorOf>central</mirrorOf>        
   </mirror>
   ```
   
2. 创建数据库 lsp并导入sql文件，修改application-druid.yml中数据库密码

3. 启动Redis数据库，如设置密码请在application.yml中补充密码

4. 运行LspApplication.java
#### 前端运行

```
cd lsp_ui

# 强烈建议不要用直接使用cnpm安装，会有各种诡异的 bug,可以通过重新指定registry来解决npm安装速度慢的问题。
npm run install --registry=https://registry.npm.taobao.org

# 启动项目
npm run dev
```

5. 打开浏览器，输入http://localhost (默认账户admin/admin123),若能正确显示登录页面和验证码，登录后各页面正常访问则项目搭建成功。

**Tip**

```
1.如果验证码不显示，请检查Redis数据库是否启动，或是否连接成功。
2.后端项目启动失败请检查端口是否被占用
```
### 项目部署

下面介绍linux环境中部署jar的方式，服务器采用nginx代理前后端。默认`mysql`、`redis`、`jdk`、`nginx`已安装成功(没有安装的请自行查询相关教程)

#### 后端项目打包

1. 在后端项目根目录下运行 `mvn clean package -Dmaven.test.skip=true`   在项目下生成target文件夹，里面有jar或war(通过修改pom配置)

2. 在linux服务器中创建项目文件夹，将`jar`包和`lsp.sh`上传当前文件夹使用命令 **java -jar lsp.jar**（界面退出，项目运行终止） 或者执行脚本`./lsp.sh start`启动 、`./lsp.sh stop `停止运行 、`./lsp.sh status `查看运行状态

**Tip**

```
lsp.sh上传后执行  chmod 777 lsp.sh  修改文件权限
```

#### 前端项目打包

1. 在lsp_ui文件夹下执行命令 npm run build:prod --report将前端项目打包,将会在前端目录下生成一个dist文件夹。

2. 将dist文件夹复制到后端项目文件夹下。

#### 配置Nginx

```
server {
	listen	80;
	server_name	********;
	#charset koi8-r;
	autoindex on;
        #access_log  logs/host.access.log  main;

        location / {
            root   /usr/local/springboot/lsp/dist;#dist目录
	    	try_files $uri $uri/ /index.html; #防止刷寻404
            index  index.html index.htm;
        }
		location /prod-api/{
	    	proxy_set_header HOST $host;
            proxy_set_header X-Forwarded-Proto $scheme;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
	    	proxy_set_header REMOTE-HOST $remote_addr;
	    	proxy_pass http://localhost:8080/;
	}
}
```

重启nginx服务器，在浏览器中输入ip或域名即可访问。

**Tip**

```
如出现错误请检查application及application-druid文件中配置是否是Linux环境下的
```


## 项目介绍

### 主要特性

- 完全响应式布局（支持电脑、平板、手机等所有主流设备）
- 支持按钮及数据权限，可自定义部门数据权限。
- 对常用js插件进行二次封装，使js代码变得简洁，更加易维护
- 完善的XSS防范及脚本过滤，彻底杜绝XSS攻击
- Maven多项目依赖，模块及插件分项目，尽量松耦合，方便模块升级、增减模块。
- 完善的日志记录体系简单注解即可实现

### 技术选型

1. 系统环境
- `Java EE 8`

* `Servlet 3.0`
* `Apache Maven 3`
2. 主框架
* `Spring Boot 2.1`
* `Spring Framework 5.1`
* `Spring Security 5.1`
3. 持久层
* `Apache MyBatis 3.4`
* `Alibaba Druid 1.1`
4. 视图层
* `Vue 4.1.1`

* `Axios 0.18`

* `Element UI 2.11`

### 内置功能

- 用户管理：用户是系统操作者，该功能主要完成系统用户配置。

- 部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。

- 职业管理：配置系统用户所属担任职务。

- 菜单管理：配置系统菜单，操作权限，按钮权限标识等。

- 角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。

- 字典管理：对系统中经常使用的一些较为固定的数据进行维护。

- 参数管理：对系统动态配置常用参数。

- 通知公告：系统通知公告信息发布维护。

- 操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。

- 登录日志：系统登录日志记录查询包含登录异常。

- 在线用户：当前系统中活跃用户状态监控。

- 代码生成：前后端代码的生成（java、html、xml、sql)支持CRUD下载 。

- 系统接口：通过`Swagger`注释根据业务代码自动生成相关的api接口文档。

- 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。

- 连接池监视：监视当期系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。


### 文件结构

#### 后端结构

```
|-- com.lsp
|       |-- common				// 工具类
|       |   |-- constant					// 通用常量
|       |   |-- core						// 核心控制
|       |   |-- enums						// 通用枚举
|       |   |-- exception					// 通用异常
|       |   |-- utils						// 通用类处理
|       |   |-- xss							// xss过滤处理
|       |-- framework			// 框架核心
|       |   |-- aspectj						// 注解实现
|       |   |-- config						// 系统配置
|       |   |-- datasource					// 数据权限
|       |   |-- manager						// 异步处理
|       |   |-- redis						// 缓存处理
|       |   |-- security					// 权限控制
|	    |   |-- web							// 前端控制
|       |-- project				// 系统模块
|           |-- common						// 通用处理
|           |-- monitor						// 系统监控
|           |-- system						// 系统管理
|           |-- tool						// 系统工具
|-- LspApplication.java				// 程序启动类
```

#### 前端结构
```
|--- build						// 构建相关  
|--- public						// 公共文件
|    |-- favicon.ico			// favicon图标
|    |-- index.html				// html模板
|--- src						
|    |-- api					// 请求接口
|    |-- assets					// 静态资源文件
|    |-- components				// 全局公用组件
|    |-- directive				// 全局指令
|    |-- layout					// 布局
|    |-- router					// 路由
|    |-- store					// 全局 store管理
|    |-- utils					// 全局公用方法
|    |-- views					// 页面代码
|    |-- App.vue				// 入口页面
|    |-- main.js				// 入口 加载组件 初始化等
|    |-- permission.js			// 权限管理
|    |-- settings.js			// 系统配置
|-- .editorconfig				// 编码格式
|-- .env.development			// 开发环境配置
|-- .env.production				// 生产环境配置
|-- .env.staging				// 测试环境配置
|-- .eslintignore				// 忽略语法检查
|-- .eslintrc.js				// eslint 配置项
|-- .gitignore					// git 忽略项
|-- babel.config.js				
|-- package.json
|-- vue.config.js
```

## 核心技术

```
前端技术栈 vue、vuex、vue-router、vue-cli、axios、element-ui、ES6
后端技术栈 SpringBoot、MyBatis、Spring Security、Jwt
```

### 后端技术

```
SpringBoot框架
```

1. 介绍
   Spring Boot是一款开箱即用框架，提供各种默认配置来简化项目配置。让我们的Spring应用变的更轻量化、更快的入门。 在主程序执行main函数就可以运行。你也可以打包你的应用为jar并通过使用`java -jar`来运行你的Web应用。它遵循"约定优先于配置"的原则， 使用SpringBoot只需很少的配置，大部分的时候直接使用默认的配置即可。可以与Spring Cloud的微服务无缝结合。

```
Spring Boot2.0 环境要求必须是jdk8或以上版本，Tomcat8或以上版本
```

2. 优点

- 使编码变得简单： 推荐使用注解。
- 使配置变得简单： 自动配置、快速构建项目、快速集成新技术能力 没有冗余代码生成和XML配置的要求
- 使部署变得简单： 内嵌Tomcat、Jetty、Undertow等web容器，无需以war包形式部署
- 使监控变得简单： 自带项目监控

### 前端技术

- npm：node.js的包管理工具，用于同一管理我们前端项目中需要用到的包、插件、工具、命令等，便于开发和维护。
- ES6：Javascript的新版本，ECMAScript6的简称。利用ES6我们可以简化我们的JS代码，同时利用其提供的强大功能来快速实现JS逻辑。
- vue-cli：Vue的脚手架工具，用于自动生成Vue项目的目录及文件。
- vue-router： Vue提供的前端路由工具，利用其我们实现页面的路由控制，局部刷新及按需加载，构建单页应用，实现前后端分离。
- vuex：Vue提供的状态管理工具，用于同一管理我们项目中各种数据的交互和重用，存储我们需要用到数据对象。
- element-ui：基于MVVM框架Vue开源出来的一套前端ui组件。

## 后端

### 分页实现

#### 前端调用的实现

```
// 在查询参数中定义分页变量
queryParams: {
  pageNum: 1,
  pageSize: 10
},

// 页面添加分页组件，传入分页变量
<pagination
  v-show="total>0"
  :total="total"
  :page.sync="queryParams.pageNum"
  :limit.sync="queryParams.pageSize"
  @pagination="getList"
/>

// 调用后台方法，传入参数 获取结果
listUser(this.queryParams).then(response => {
    this.userList = response.rows;
    this.total = response.total;
  }
);
```

#### 后端逻辑实现

```
@PostMapping("/list")
@ResponseBody
public TableDataInfo list(User user)
{
    startPage();  // 此方法配合前端完成自动分页
    List<User> list = userService.selectUserList(user);
    return getDataTable(list);
}
```

### 导入导出

在实际开发中经常需要使用导入导出功能来加快数据的操作。在项目中可以使用注解来完成此项功能。 在需要被导入导出的实体类属性添加`@Excel`注解，目前支持参数如下：

| 参数              | 类型   | 默认值     | 描述           |
| ---------------- | ------ | --------- | ------------------------ |
| name             | String | 空        | 导出到Excel中的名字         |
| dateFormat       | String | 空        | 日期格式, 如: yyyy-MM-dd   |
| readConverterExp | String | 空        | 读取内容转表达式 (如: 0=男,1=女,2=未知)     |
| columnType       | Enum   | Type.STRING | 导出类型（0数字 1字符串）   |
| height           | String | 14    | 导出时在excel中每个列的高度 单位为字符 |
| width            | String | 16    | 导出时在excel中每个列的宽 单位为字符  |
| suffix           | String | 空    | 文字后缀,如% 90 变成90%       |
| defaultValue     | String | 空    | 当值为空时,字段的默认值         |
| prompt           | String | 空    | 提示信息                     |
| combo            | String | Null  | 设置只能选择不能输入的列内容    |
| targetAttr       | String | 空     |另一个类中的属性名称,支持多级获取,以小数点隔开 |
| type             | Enum   | Type.ALL    | 字段类型（0：导出导入；1：仅导出；2：仅导入）  |

#### 导出实现流程

1. 在实体变量上添加@Excel注解

   ```
   @Excel(name = "用户序号", prompt = "用户编号")
   private Long userId;
   
   @Excel(name = "用户名称")
   private String userName;
   	
   @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
   private String sex;
   ```
   
   
   
2. 在Controller添加导出方法

   ```
   @Log(title = "用户管理", businessType = BusinessType.EXPORT)
   @PreAuthorize("@ss.hasPermi('system:user:export')")
   @GetMapping("/export")
   public AjaxResult export(SysUser user)
   {
   	List<SysUser> list = userService.selectUserList(user);
   	ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
   	return util.exportExcel(list, "用户数据");
   }
   ```

   

3. 前端调用方法

   ```
   // 导出接口exportUser
   import { exportUser } from "@/api/system/user";
   
   // 查询参数 queryParams
   queryParams: {
     pageNum: 1,
     pageSize: 10,
     userName: undefined
   },
   
   /** 导出按钮操作 */
   handleExport() {
     const queryParams = this.queryParams;
     this.$confirm('是否确认导出所有用户数据项?', "警告", {
   	  		confirmButtonText: "确定",
   	  		cancelButtonText: "取消",
   	  		type: "warning"
   		}).then(function() {
   	  		return exportConfig(queryParams);
   		}).then(response => {
   	  		this.download(response.msg);
   		}).catch(function() {});
   	}
   }
   ```

   

4. 添加导出按钮事件

   ```
   <el-button
     type="warning"
     icon="el-icon-download"
     size="mini"
     @click="handleExport"
   >导出</el-button>
   ```

#### 导入实现流程

1. 在实体变量上添加@Excel注解，默认为导出导入，也可以单独设置仅导入Type.IMPORT

   ```
   @Excel(name = "用户名称")
   private String userName;
   
   /** 导出部门多个对象 */
   @Excels({
   	@Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
   	@Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
   })
   private SysDept dept;
   
   /** 导出部门单个对象 */
   @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT)
   private SysDept dept;
   ```

2. 在Controller添加导入方法，updateSupport属性为是否存在则覆盖（可选）

   ```
   @Log(title = "用户管理", businessType = BusinessType.IMPORT)
   @PostMapping("/importData")
   public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
   {
   	ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
   	List<SysUser> userList = util.importExcel(file.getInputStream());
   	LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
   	String operName = loginUser.getUsername();
   	String message = userService.importUser(userList, updateSupport, operName);
   	return AjaxResult.success(message);
   }
   
   @GetMapping("/importTemplate")
   public AjaxResult importTemplate()
   {
   	ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
   	return util.importTemplateExcel("用户数据");
   }
   ```

3. 前端调用方法
	```
//在/api/system/user.js中添加api
	// 下载用户导入模板
	export function importTemplate() {
	  return request({
	    url: '/system/user/importTemplate',
	    method: 'get'
	  })
	}
	
	// 导入模板接口importTemplate
	import { importTemplate } from "@/api/system/user";
	
	// 用户导入参数
	upload: {
	  // 是否显示弹出层（用户导入）
	  open: false,
	  // 弹出层标题（用户导入）
	  title: "",
	  // 是否禁用上传
	  isUploading: false,
	  // 是否更新已经存在的用户数据
	  updateSupport: 0,
	  // 设置上传的请求头部
	  headers: { Authorization: "Bearer " + getToken() },
	  // 上传的地址
	  url: process.env.VUE_APP_BASE_API + "/system/user/importData"
	},
	
	/** 导入按钮操作 */
	handleImport() {
	  this.upload.title = "用户导入";
	  this.upload.open = true;
	},
	/** 下载模板操作 */
	importTemplate() {
	  importTemplate().then(response => {
		this.download(response.msg);
	  });
	},
	// 文件上传中处理
	handleFileUploadProgress(event, file, fileList) {
	  this.upload.isUploading = true;
	},
	// 文件上传成功处理
	handleFileSuccess(response, file, fileList) {
	  this.upload.open = false;
	  this.upload.isUploading = false;
    this.$refs.upload.clearFiles();
	  this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
	  this.getList();
	},
	// 提交上传文件
	submitFileForm() {
	  this.$refs.upload.submit();
	}
	```
	
4. 添加导入按钮事件

   ```
   <el-button
     type="info"
     icon="el-icon-upload2"
     size="mini"
     @click="handleImport"
   >导入</el-button>
   ```

5. 添加导入前端代码

   ```
   <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px">
     <el-upload
   	ref="upload"
   	:limit="1"
   	accept=".xlsx, .xls"
   	:headers="upload.headers"
   	:action="upload.url + '?updateSupport=' + upload.updateSupport"
   	:disabled="upload.isUploading"
   	:on-progress="handleFileUploadProgress"
   	:on-success="handleFileSuccess"
   	:auto-upload="false"
   	drag
     >
   	<i class="el-icon-upload"></i>
   	<div class="el-upload__text">
   	  将文件拖到此处，或
   	  <em>点击上传</em>
   	</div>
   	<div class="el-upload__tip" slot="tip">
   	  <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的用户数据
   	  <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
   	</div>
   	<div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
     </el-upload>
     <div slot="footer" class="dialog-footer">
   	<el-button type="primary" @click="submitFileForm">确 定</el-button>
   	<el-button @click="upload.open = false">取 消</el-button>
     </div>
   </el-dialog>
   ```

   