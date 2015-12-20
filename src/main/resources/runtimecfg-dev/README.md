## Description

开发的人员定义的配置文件夹, 配置参数按照自己的环境定义, 第一次配置时, 需手动从runtimecfg-template中拷贝文件至此处, 打包时, 会默认打包至runtimecfg内.

此文件夹将在第一次提交Git后, 设置.gitignore, 所以每个人的配置均不会影响别人. 后续配置如果变更, 需要修改定义的runtimecfg-template, 并更新runtimecfg-qa 和 runtimecfg-product
