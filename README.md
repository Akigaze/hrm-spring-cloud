# hrm-admin 改造成 spring cloud 项目

## 项目结构

- 服务注册中心项目：eureka-server
- 服务配置中心项目：config-server
- 服务网关项目：gateway-server
- 两种服务项目（各两份，不同端口）：employee-service 、department-service





## TODO

- [ ] 在config-cloud仓库上配置其他project的yml
- [ ] hrm-admin拆分成两个service
- [ ] 拆分完一个，先通过gateway访问employee-service测试一波
- [ ] 前端axios访问gateway，查看是否有跨域问题
- [ ] 最后统一把前端访问的url，改成访问gateway project