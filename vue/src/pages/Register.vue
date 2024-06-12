<template>
  <div>
    <div style="width: 400px;margin: 10% auto;color: var(--loginText)">
      <el-form :model="user" :rules="rules" ref="userForm">
        <h1 style="font-size: 30px;margin: 10px 0;">注册一个账户</h1>
        <span style="font-weight: 600;font-size: 13px">
        已有账户？
        <span class="register"
              style="cursor: pointer;
              color: var(--loginA);padding: 2px 5px 5px 5px;
              border-radius: 5px"
              @click="$router.push('/login')">
          直接登录
        </span>
      </span>
        <el-form-item prop="email">
          <el-input
              placeholder=" 邮箱"
              prefix-icon="el-icon-message"
              style="margin-top: 30px;background-color: var(--loginInputBg);width: 350px;font-size: 14px"
              v-model="user.email"
              clearable
              @keydown.enter.native="register"
          />
        </el-form-item>
        <el-form-item prop="username">
          <el-input
              placeholder=" 用户名"
              prefix-icon="el-icon-user"
              style="margin-top: 20px;background-color: var(--loginInputBg);width: 350px;font-size: 14px"
              v-model="user.username"
              clearable
              maxlength="20"
              show-word-limit
              @keydown.enter.native="register"
          />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              placeholder=" 密码"
              prefix-icon="el-icon-lock"
              style="margin-top: 20px;background-color: var(--loginInputBg);width: 350px;font-size: 14px"
              show-password
              v-model="user.password"
              clearable
              @keydown.enter.native="register"
          />
        </el-form-item>
        <el-button style="margin-top: 20px;width: 350px;
                      height: 40px;background-color: var(--loginA);
                      border: none;color: var(--btnText);font-weight: bold;
                      font-size: 14px;transition: 0.4s"
                   @click="register">
          立即注册
        </el-button>
      </el-form>
    </div>
  </div>
</template>

<script>`                              `
export default {
  name: "Register",
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
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    register() {
      this.$refs["userForm"].validate((valid) => {
        //发送请求前先检查是否合法
        if (valid) {
          this.request.post("/user/register", this.user).then((response) => {
            if (response.code !== '200') {
              // 注册失败
              this.$notify({
                message: response.msg,
                type: 'error',
                title: '注册失败'
              })
            } else {
              //注册成功
              this.$router.push('/login')
              this.$notify({
                title: '成功',
                message: '注册成功',
                type: 'success'
              });
            }
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

/deep/ .el-input__count-inner {
  background-color: var(--loginInputBg) !important;
}

</style>