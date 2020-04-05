RBAC REST
===

User -- Role -- Project
Role -- Permission

== 》 User - Project - Permission

待解决问题
-----

- [ ] `GET projects/{projectId}` 的请求要根据用户在项目中承担的角色
- [ ] `GET companies/{companyId}` company中的角色
- [ ] `GET pipelines` 自身有Processor角色，且返回结果仅仅包含参与的项目
- [ ] `POST users` 只有Admin可以做这个操作

