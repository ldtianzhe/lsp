<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
        <el-form-item label="认证类型" prop="verifyType">
        <el-select v-model="queryParams.verifyType" placeholder="请选择认证类型" clearable size="small">
          <el-option
            v-for="dict in verifyTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
        <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['system:verify:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:verify:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:verify:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:verify:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="verifyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="认证信息id" align="center" prop="verifyId" show-overflow-tooltip/>
      <el-table-column label="用户id" align="center" prop="useId" />
      <el-table-column label="认证类型" align="center" prop="verifyType" :formatter="verifyTypeFormat" />
      <el-table-column label="审核状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:verify:edit']"
          >查看</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['system:verify:remove']"-->
<!--          >删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

<!--    <el-dialog :title="title" :visible.sync="openName" width="500px">-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="用户姓名:" prop="useId">-->
<!--          <p v-model="form.usename"></p>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="身份账号:" prop="">-->
<!--          <p v-model="form.usename"></p>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="认证类型">-->
<!--          <el-select v-model="form.verifyType" placeholder="请选择认证类型">-->
<!--            <el-option-->
<!--              v-for="dict in verifyTypeOptions"-->
<!--              :key="dict.dictValue"-->
<!--              :label="dict.dictLabel"-->
<!--              :value="dict.dictValue"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="审核状态">-->
<!--          <el-select v-model="form.status" placeholder="请选择审核状态">-->
<!--            <el-option-->
<!--              v-for="dict in statusOptions"-->
<!--              :key="dict.dictValue"-->
<!--              :label="dict.dictLabel"-->
<!--              :value="dict.dictValue"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">审核</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->

    <!-- 添加或修改审核信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="useId">
          <el-input v-model="form.useId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="认证类型">
          <el-select v-model="form.verifyType" placeholder="请选择认证类型">
            <el-option
              v-for="dict in verifyTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核状态">
          <el-select v-model="form.status" placeholder="请选择审核状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">审核</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listVerify, getVerify, delVerify, addVerify, updateVerify, exportVerify } from "@/api/system/verify";

export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 审核信息表格数据
      verifyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 认证类型0为实名认证1为企业认证2为用电信息认证3为职业认证字典
      verifyTypeOptions: [],
      // 审核状态0未审核1已审核字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        useId: undefined,
        verifyType: undefined,
        status: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        verifyType: [
          { required: true, message: "认证类型不能为空", trigger: "blur" }
        ],        status: [
          { required: true, message: "审核状态不能为空", trigger: "blur" }
        ],      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_verify_type").then(response => {
      this.verifyTypeOptions = response.data;
    });
    this.getDicts("sys_verify_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询审核信息列表 */
    getList() {
      this.loading = true;
      listVerify(this.queryParams).then(response => {
        this.verifyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 认证类型0为实名认证1为企业认证2为用电信息认证3为职业认证字典翻译
    verifyTypeFormat(row, column) {
      return this.selectDictLabel(this.verifyTypeOptions, row.verifyType);
    },
    // 审核状态0未审核1已审核字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        verifyId: undefined,
        useId: undefined,
        verifyType: undefined,
        status: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        remark: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.verifyId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加审核信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const verifyId = row.verifyId || this.ids
      const verifyType = row.verifyType
      if (verifyType == 0){

      }else if (verifyType == 1){

      }else if (verifyType == 2){

      }else if (verifyType == 3){

      }
      getVerify(verifyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改审核信息";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.verifyId != undefined) {
            this.form.status = "1"
            updateVerify(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("审核通过");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addVerify(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const verifyIds = row.verifyId || this.ids;
      this.$confirm('是否确认删除审核信息编号为"' + verifyIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delVerify(verifyIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有审核信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportVerify(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
