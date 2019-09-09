package demo.minifly.com.fuction_demo.rxjava_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import demo.minifly.com.R;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * rxjava：异步，简洁
 * rxjava 一个异步的基于事件的库
 * 在理解上可以用onclick的listener来理解，其实就是一个序列在每次的处理的时候，放出了回调的一个库，原理比较简单。
 * 在序列开始循环的时候开始调用onstart，在每次循环的时候，调用onnext，在发生错误的时候调用onerror，结束的时候调用观察者的oncomplete方法。
 * 然后在每次调用的时候进行实现就行。
 * 没有什么特别的地方。
 */
public class RxjavaDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView rxjavaTxtId;
    private Button rxjavaBtnId;
    private StringBuilder sb;
    private Button rxjavaBtn2Id;
    private Button rxjavaBtn3Id;
    private Button rxjavaBtn4Id;
    private Button rxjavaBtn5Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo);
        initView();
    }

    private void initView() {
        rxjavaTxtId = (TextView) findViewById(R.id.rxjava_txt_id);
        rxjavaBtnId = (Button) findViewById(R.id.rxjava_btn_id);

        rxjavaBtnId.setOnClickListener(this);

        sb = new StringBuilder("");
        rxjavaBtn2Id = (Button) findViewById(R.id.rxjava_btn2_id);
        rxjavaBtn2Id.setOnClickListener(this);
        rxjavaBtn3Id = (Button) findViewById(R.id.rxjava_btn3_id);
        rxjavaBtn3Id.setOnClickListener(this);
        rxjavaBtn4Id = (Button) findViewById(R.id.rxjava_btn4_id);
        rxjavaBtn4Id.setOnClickListener(this);
        rxjavaBtn5Id = (Button) findViewById(R.id.rxjava_btn5_id);
        rxjavaBtn5Id.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rxjava_btn_id:
                sb = new StringBuilder("");
                //观察者
                Subscriber subscriber = new Subscriber() {
                    @Override
                    public void onCompleted() {
                        sb.append("完成");
                        rxjavaTxtId.setText(sb.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        sb.append(o.toString() + "\n");
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        sb.append("开始了啊" + "\n");
                    }
                };

                //被观察者
                Observable observable = Observable.create(new Observable.OnSubscribe<Object>() {
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

                /**
                 * 创建了 Observable 和 Observer 之后，再用 subscribe() 方法将它们联结起来，整条链子就可以工作了
                 */
                observable.subscribe(subscriber);//观察

                break;
            case R.id.rxjava_btn2_id:
                //btn的点击中的观察者模式中：
                /**
                 * create() 方法是 RxJava 最基本的创造事件序列的方法。基于这个方法， RxJava 还提供了一些方法用来快捷创建事件队列，例如：

                 just(T...): 将传入的参数依次发送出来。
                 Observable observable = Observable.just("Hello", "Hi", "Aloha");
                 // 将会依次调用：
                 // onNext("Hello");
                 // onNext("Hi");
                 // onNext("Aloha");
                 // onCompleted();
                 from(T[]) / from(Iterable<? extends T>) : 将传入的数组或 Iterable 拆分成具体对象后，依次发送出来。
                 String[] words = {"Hello", "Hi", "Aloha"};
                 Observable observable = Observable.from(words);
                 // 将会依次调用：
                 // onNext("Hello");
                 // onNext("Hi");
                 // onNext("Aloha");
                 // onCompleted();
                 */
                sb = new StringBuilder("");
                Subscriber subscriber1 = new Subscriber() {
                    @Override
                    public void onCompleted() {
                        sb.append("我被完成了2");
                        rxjavaTxtId.setText(sb.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        sb.append(o.toString() + "\n");
                    }
                };

                Observable observable1 = Observable.just("我2", "是2", "有顺序的2");

                observable1.subscribe(subscriber1);

                break;
            case R.id.rxjava_btn3_id:
                sb = new StringBuilder("");
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
                String[] strs = new String[]{"我3", "是3", "有顺序的3"};

                Observable observable2 = Observable.from(strs);
                //从语义上看，是被观察者订阅了观察者，但是不能为了用起来顺畅就改动，这样链式的调用就有问题。将传入的 Subscriber 作为 Subscription 返回。这是为了方便 unsubscribe()
                observable2.subscribe(subscriber2);

                break;
            /**
             * 在不指定线程的情况下， RxJava 遵循的是线程不变的原则，即：在哪个线程调用 subscribe()，就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler （调度器）。
             * 要涉及到异步与后台相关的东西就应该看看调度器相关，在调度器中，可以指定observable 与 subscrber所在的线程。
             *
             * scheduler的分类.
             *   Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
             Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
             Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
             Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
             另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
             */

            case R.id.rxjava_btn4_id:
                //链式的方式写一次
                sb = new StringBuilder("");
                Observable.just("我4", "还是4", "有4", "顺序的4")
                        .subscribeOn(Schedulers.io())//指定订阅的线程在io线程中
                        .observeOn(AndroidSchedulers.mainThread()) //指定subscriber回调发生在主线程中。
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                sb.append("我也完成了4");
                                rxjavaTxtId.setText(sb.toString());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String s) {
                                sb.append(s);
                            }

                            @Override
                            public void onStart() {
                                super.onStart();
                                sb.append("我开始了 4: ");
                            }
                        });


                sb.append(" \n ");

//                Observable : 可观察者 Observer ： 观察者  subscribe ： 订阅   事件 ；Observable 和 Observer 通过 subscribe() 方法实现订阅关系，从而 Observable 可以在需要的时候发出事件来通知 Observer
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        //实现被观察者的实现；
                        subscriber.onNext("我5");
                        subscriber.onNext("是5");
                        subscriber.onNext("一个5");
                        subscriber.onNext("链式的调用5");
                        subscriber.onCompleted(); //调用了之后才会起反应；
                    }
                }).subscribeOn(Schedulers.io())//指定订阅的线程在io线程中
                    .observeOn(AndroidSchedulers.mainThread()) //指定subscriber回调发生在主线程中。
                    .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                sb.append("我打印完了5");
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String o) {
                                sb.append("" + o);
                            }

                            @Override
                            public void onStart() {
                                super.onStart();
                                sb.append("开始了啊5");
                            }
                        });


                Observable observable3 = Observable.just("呜" , "哈" , "哈" );
                Observer observer = new Observer() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.e("mainactivity" , o.toString());
                    }
                };
                observable3.subscribe(observer);

                break;
            //要在不再使用的时候尽快在合适的地方（例如 onPause() onStop() 等方法中）调用 unsubscribe() 来解除引用关系，以避免内存泄露的发生。
            /**
             * 绑定观察者与被观察者的方式只有一种，就跟设置回调一样，一个方法来绑定即可了
             * observable.subscribe(observer);
             * observable.subscribe(subscriber);
             */

            /**
             * just() 与 from() 跟之前的订阅的create方式是等价的；
             */

            case R.id.rxjava_btn5_id:

                List<String > dest = new LinkedList<>();
                dest.add("5顺");
                dest.add("5序");
                dest.add("5显示");
                Observable.from(dest).subscribe(new Action1<String>(){
                    @Override
                    public void call(String s) {
                        Log.e("mainactivity" , "" + s);
                    }
                });


                /**
                 * 转换的方式； map方式
                 */
                Observable.just("10" , "21" ).map(s -> Integer.parseInt(s)).subscribe(integer -> Log.e("mainactivity" , integer.toString()));

                /**
                 * 转换的方式： flatmap
                 */
                List<Student> students = new LinkedList<>();
                students.add(new Student("142","王老师","语文"));
                students.add(new Student("142","李老师","数学"));
                students.add(new Student("142","张老师","英语"));
                students.add(new Student("143","王老师","语文"));

                Observable.just(students).flatMap((Func1<List<Student>, Observable<Student>>) students1 -> Observable.from(students1)).subscribe(student -> {

                });


                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Unsubscribed.
    }

    class Student{
        String number ;
        String name ;
        String course;

        public Student(String number, String name, String course) {
            this.number = number;
            this.name = name;
            this.course = course;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }
    }
}
