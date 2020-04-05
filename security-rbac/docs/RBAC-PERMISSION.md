RBAC PERMISSION
===

用户和角色直接绑定，但赋予权限时为按照角色赋予，可以对具体的权限就行微调。

目前看到的一些例子都是实现为：一个角色具有访问某个url的权限，没有对url做特别的说明。  

可进一步探究的部分：

- 一个电商网站，用户可以访问自己的个人信息
- 一个电商网站，用户只可以访问自己的order列表，购物车列表
- 一个电商网站，网站业务人员可以访问到已经下单的数据
- 一个电商网站，管理员可以管理业务人员

后台系统的认证授权和客户端的认证授权是不一样的。

后台系统的认证授权，用户可以访问所有的类型的资源，级别在类型。

而客户端系统的授权则不同。级别在具体的某个资源。  


GET /projects/{projectId}/xxx

作为x角色可以访问的链接
Role(Admin) - Permission(GET, "/users")

Admin可以访问所有的project
Role(Admin) - Permission(GET, "/projects/{projectId}/address")

Company Admin可以访问属于他负责的公司的所有的project
Role(Company Admin) - Permission(GET, "/projects/{projectId}/address")

在projectId中担任x角色可以访问的链接
Role(Pilot) - Permission(GET, "/projects/{projectId}/address", "@SC.isMember(projectId)")

职业授权
职责授权