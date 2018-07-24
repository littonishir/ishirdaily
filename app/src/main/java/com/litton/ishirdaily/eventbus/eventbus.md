# EventBus

我的目之所及

### 情景一 退出登录关闭所有的activity返回登录页面

- Demo入口->LoginActivity

1. 定义ActivityFinishEvent事件

1. 创建BaseEventBusActivity处理EventBus的注册注销以及订阅方法

1. 其他Activity继承自BaseEventBusActivity即可

1. 在需要关闭所有的activity发送定义ActivityFinishEvent事件

```
    EventBus.getDefault().post(new ActivityFinishEvent(""));
```

