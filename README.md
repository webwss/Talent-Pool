- - # Talent-Pool
  
    企业面临人才管理效率低，人才资源配置不合理等问题，需开发一套系统来整合人才信息，实现精准管理、高效搜索与筛选，同时确保数据的安全和隐私保护，以支持企业人才战略提供有力支持。
  
    ## 文件说明
  
    backend：后端项目
  
    frontend：前端项目
  
    Talent-Pool：数据库文件
  
    ## 技术栈
  
    基于企业微信开发
  
    前端：Vue3 + And Design Vue + AntV
  
    后端：Spring Boot
  
    数据库：MySQL + Redis
  
    AI：通义千问图片理解模型
  
    数据库文件：Talent-Pool.sql
  
    ## 主要功能
  
    - 基于企业微信
      - **企业微信扫码注册登录**
      - **HR每日新增人才数量推送**
      - **当日人才面试提醒**
    - 人才库管理
      - 新增人才信息
      - **批量导入人才信息**
      - 编辑人才信息
      - 删除人才信息
      - **人才信息PDF导出**
      - **简历相关面试题生成（AI图片理解大模型）**
    - 数据看板
      - 岗位-人才数看板
      - 性别-人才数看板
      - 学历-人才数看板
      - 近七日新增人才趋势看板
    - 岗位管理
    - 员工管理
    - 人才删除审批
  
    ## 部分截图
  
    - 登录
  
    ![image-20250624094014189](https://github.com/webwss/Talent-Pool/img/image-20250624094014189.png)
  
    - 数据看板
  
    ![image-20250624094501668](https://github.com/webwss/Talent-Pool/img/image-20250624094501668.png)
  
    - 人才库
  
    ![image-20250624094632406](https://github.com/webwss/Talent-Pool/img/image-20250624094632406.png)
  
    - 人才详情
  
    ![image-20250624094656794](https://github.com/webwss/Talent-Pool/img/image-20250624094656794.png)
  
    - 新增人才
  
    ![image-20250624094712087](https://github.com/webwss/Talent-Pool/img/image-20250624094712087.png)
  
    ## 使用说明
  
    后端项目需要新建配置文件：application-local.yml，内容如下
  
    ```yml
    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/talent_pool # 你自己的数据库地址
        username: root
        password: 123456
      session:
        store-type: redis
        timeout: 2592000
      redis:
        database: 0
        host: localhost # 你自己的Redis地址
        port: 6379
        password: 123456
        timeout: 3000
      servlet:
        multipart:
          max-file-size: 50MB
          max-request-size: 50MB
    
    wx:
      corpId: "corpId" # 企业微信corpId
      corpSecret: "corpSecret"  # 企业微信corpSecret
    qn:
      accessKey: "accessKey" # 七牛云accessKey
      secretKey: "secretKey-dtM" # 七牛云secretKey
      bucket: "bucket" # 七牛云bucket
      domain: "domain" # 七牛云域名地址
      path: "path/" # 七牛云文件夹
    ai:
      openai:
        apiKey: "sk-xxxxxx" # 阿里云百炼apiKey
    ```
  
    前端项目需要修改LoginPage.vue中的微信信息
  
    ```javascript
      // 初始化登录组件
      const wwLogin = ww.createWWLoginPanel({
        el: weChatCodeLogin.value,
        params: {
          login_type: 'CorpApp',
          appid: 'xxx', // 替换为实际的CorpApp的appid
          agentid: 'xxx', // 替换为实际的CorpApp的agentid
          redirect_uri: 'xxx', // 替换为实际的回调地址
          state: 'loginState', // 登录状态
          redirect_type: 'callback', // 回调类型
        },
        onCheckWeComLogin({ isWeComLogin }) {
          console.log(isWeComLogin)
        },
        onLoginSuccess({ code }) {
          console.log({ code })
          handleLoginCallback(code)
        },
        onLoginFail(err) {
          console.log(err)
        },
      })
    ```
  
    ### 贡献者
  
    - [爽爽同学](https://github.com/webwss) — 主要开发者
  
    ### 联系方式
  
    - 如有任何疑问或建议，欢迎联系项目主要开发者：
      - 电子邮箱：[webwss@qq.com](mailto:webwss@qq.com)