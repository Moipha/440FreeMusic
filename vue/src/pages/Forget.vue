<template>
  <div style="width: 400px;margin: auto;color: var(--loginText)">
    <h1 style="font-size: 35px;text-align: center">找回密码</h1>
    <el-input placeholder="注册邮箱（必填）" prefix-icon="el-icon-message" style="margin-top: 30px" v-model="email"/>
    <el-button style="width: 200px;margin: 20px 100px 0 100px;
                      height: 40px;background-color: var(--loginBtn);
                      border: none;color: var(--loginText);font-weight: bold;
                      font-size: 14px;transition: 0.4s">找回密码</el-button>
  </div>
</template>

<script>
export default {
  name: "Forget",
  data(){
    return{
      email: '',
    }
  }
}
</script>

<style scoped>
/deep/ .el-input__inner::placeholder {
  color: var(--loginInputText);
}

/deep/ .el-input__icon {
  color: var(--loginText)
}
/deep/ .el-input__inner {
  padding: 0 40px;
  margin: 1px 0;
  color: var(--loginText);
  background-color: var(--loginInputBg);
  width: 400px;
  border: var(--loginInputBorder) solid 1px;
  border-radius: 0;
}

/deep/ .el-input__inner:focus {
  border-color: var(--loginInputActive);
}
/deep/ .el-button:hover {
  background-color: var(--loginBtnHover)!important;
}
</style>