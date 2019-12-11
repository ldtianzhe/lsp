<template>
  <div class="app-container">
    <el-row>
      <el-col :span="2">
        <p>姓名:</p>
      </el-col>
      <el-col :span="2">
        <p v-text="name"></p>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="2">
        <p>身份证号:</p>
      </el-col>
      <el-col :span="2">
        <p v-text="idNumber"></p>
      </el-col>
    </el-row>

    <div style="margin-top: 100px">
      <el-button type="success" v-if="verified">已审核</el-button>
      <el-button type="primary" v-else @click="submitForm">审核</el-button>
      <el-button @click="close">取 消</el-button>
    </div>
  </div>
</template>

<script>
  import {listName, getName, getInfoByUserId, delName, addName, updateName, exportName} from "@/api/system/name";
  import {listVerify, getVerify, delVerify, addVerify, updateVerify, exportVerify} from "@/api/system/verify";

  export default {
    name: "VerifyName",
    data() {
      return {
        verifyId: undefined,
        name: undefined,
        idNumber: undefined,
        verified: undefined,
        form: {}
      }
    },
    created() {
      const {verifyId, userId, status} = this.$route.query;
      this.userId = userId
      this.verifyId = verifyId
      if (status == 1) {
        this.verified = true
      }
      getInfoByUserId(userId).then(response => {
        this.name = response.data.name
        this.idNumber = response.data.idNumber
      });
      getVerify(verifyId).then(response => {
        this.form = response.data;
      });
    },
    methods: {
      // 表单重置
      reset() {
        this.form = {
          verifyId: undefined,
          userId: undefined,
          verifyType: undefined,
          status: undefined,
          createBy: undefined,
          createTime: undefined,
          updateBy: undefined,
          updateTime: undefined,
          remark: undefined
        };
        this.verifyId = undefined;
        this.userId = undefined;
        this.idNumber = undefined;
        this.status = undefined;
      },
      submitForm: function () {
        if (this.form.verifyId != undefined) {
          this.form.status = "1"
          updateVerify(this.form).then(response => {
            if (response.code === 200) {
              this.msgSuccess("审核通过");
              this.reset()
              this.close();
            } else {
              this.msgError(response.msg);
            }
          });
        }
      },
      /** 关闭按钮 */
      close() {
        this.reset()
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({path: "/system/verify", query: {t: Date.now()}})
      }
    }
  }
</script>

<style scoped>

</style>
