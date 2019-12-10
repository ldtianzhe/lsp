<template>
<!--  <el-form ref="form" label-width="80px">-->
<!--    <el-form-item label="姓名" prop="name">-->
<!--      <el-input v-model="name" placeholder="请输入姓名"/>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="身份证号" prop="idNumber">-->
<!--      <el-input v-model="idNumber" placeholder="请输入身份证号"/>-->
<!--    </el-form-item>-->
<!--  </el-form>-->
<!--  <div slot="footer" >-->
<!--    <el-button type="primary" @click="submitForm">审核</el-button>-->
<!--    <el-button @click="close">取 消</el-button>-->
<!--  </div>-->
</template>

<script>
  export default {
    name: "VerifyName",
    data() {
      return{
        name: "",
        idNumber: "",
      }
    },
    beforeCreate() {

    },
    methods: {
      submitForm: function () {
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
        }

      },
      /** 关闭按钮 */
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({ path: "/tool/gen", query: { t: Date.now()}})
      }
    }
  }
</script>

<style scoped>

</style>
