<template>
  <div v-loading="loading">
    <div style="width: 400px;margin: 10% auto">
      <el-form :model="user" :rules="rules" ref="userForm" style="color: var(--loginText)">
        <h1 style="font-size: 30px;margin: 10px 0;">登录</h1>
        <span style="font-weight: 600;font-size: 13px">
          没有账户？
          <span class="register"
                style="cursor: pointer;
                color: var(--loginA);padding: 2px 5px 5px 5px;
                border-radius: 5px"
                @click="$router.push('/register')">免费注册</span>
        </span>
        <el-form-item prop="username">
          <el-input
              placeholder=" 用户名/邮箱"
              prefix-icon="el-icon-user"
              style="margin-top: 30px;background-color: var(--loginInputBg);width: 350px;font-size: 14px"
              v-model="user.username"
              clearable
              @keydown.enter.native="login"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              placeholder=" 密码"
              prefix-icon="el-icon-lock"
              style="margin-top: 20px;background-color: var(--loginInputBg);width: 350px;font-size: 14px"
              show-password
              v-model="user.password"
              clearable
              @keydown.enter.native="login"/>
        </el-form-item>

        <el-button style="margin-top: 20px;width: 350px;
                      height: 40px;background-color: var(--loginBtn);
                      border: none;color: var(--loginText);font-weight: bold;
                      font-size: 14px;transition: 0.4s"
                   @click="login">登录
        </el-button>
        <h5 class="register" style="float:right;margin-right: 50px;
          cursor: pointer;color: var(--loginA);
          padding: 2px 5px 5px 5px;border-radius: 5px"
            @click="$router.push('/forget')">忘记密码?</h5>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
      },
      //加载中
      loading: false,
    }
  },
  methods: {
    login() {
      this.$refs["userForm"].validate((valid) => {
        //发送请求前先检查是否合法
        if (valid) {
          this.loading =  true
          this.request.post("/user/login", this.user).then(response => {
            if (response.code !== '200') {
              //登陆失败
              this.$notify({
                title: '登录失败',
                message: response.msg,
                type: 'error'
              })
              this.loading = false
            } else {
              //登录成功
              //将传入的用户信息存入本地内存
              localStorage.setItem('user', JSON.stringify(response.data))
              this.$router.push("/home")
              //弹窗
              this.$notify({
                title: '成功',
                message: '登录成功',
                type: 'success'
              });
              //修改边栏文字
              this.$bus.$emit('logout')
              this.loading = false
            }
          }).catch(err => {
            this.$notify({
              title: '登录失败',
              message: err,
              type: 'error'
            });
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>
/deep/ .el-input__inner {
  padding: 0 40px;
  margin: 1px 0;
  color: var(--loginText);
  background-color: var(--loginInputBg);
  width: 350px;
  border: var(--loginInputBorder) solid 1px;
  border-radius: 0;
}

/deep/ .el-input__inner:focus {
  border-color: var(--loginInputActive);
}

.register:hover {
  background-color: var(--loginAHover);
}

/deep/ .el-input__inner::placeholder {
  color: var(--loginInputText);
}

/deep/ .el-input__icon {
  color: var(--loginText)
}

/deep/ .el-button:hover {
  background-color: var(--loginBtnHover) !important;
}

/deep/ .el-loading-mask {
  margin: auto;
  padding: 0;
  width: 50%;
  height: 150%;
  background-color: var(--rightBg);
  border-radius: 15px;
}

</style>