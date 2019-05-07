

rxjava 使用api初探：

1.创建循环数据集中方式；
    a.create 直接一个个的进行创建
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                //会按顺序进行调用
                subscriber.onNext("我");
                subscriber.onNext("是");
                subscriber.onNext("有顺序的");
                subscriber.onNext(1000001001);
                subscriber.onCompleted();
            }
        });

    b.just 给一个列表

    c.from 给一个列表 跟just没有本质区别

2.创建可观察者 obserble；
    a.create方法
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                //会按顺序进行调用
                subscriber.onNext("我");
                subscriber.onNext("是");
                subscriber.onNext("有顺序的");
                subscriber.onNext(1000001001);
                subscriber.onCompleted();
            }
        });

    b.from方法，给一个列表
        Observable observable2 = Observable.from(strs);
    c.just方法 ， 同from方法

3.创建观察者  observe
    a.直接new出来；
        Subscriber subscriber2 = new Subscriber() {
            @Override
            public void onCompleted() {
                sb.append("完成了3");
                rxjavaTxtId.setText(sb.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                sb.append("" + o.toString());

            }

            @Override
            public void onStart() {
                sb.append("我开始了啊");
                super.onStart();
            }


        };
    b.直接new给每个action的动作代码；
        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                Log.d(tag, s);
            }
        };
        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
    c.用替换类 Subscriber 替换observe；
        observable.subscribe(new Subscriber());


4.指定观察者和被观察者的运行线程
    Scheduler

    subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
    observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。

5.转换
    map() 是一对一的转化
    flatmap() 一对多的转换
















