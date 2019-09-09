package com.demo.hexiaofei.handframecode.handler_frame;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 维护一个装载所有message的列表；
 */
public class MessageQueue {

    private Message[] messages;
    private Condition pullQueueCondition;
    private Condition pushQueueCondition;

    Lock lock;//互斥锁； 互斥锁只有两种状态,即上锁( lock )和解锁( unlock )

    /**
     * 同步 异步 互斥
     * 同步：执行有先后顺序
     * 异步：执行没有先后与执行的状态相关性，可以自由执行
     * 互斥：有你没我，誓不存一的状态；
     */

    private int pullIndex, pushIndex, count;

    public MessageQueue() {
        messages = new Message[50];
        lock = new ReentrantLock();
        pullQueueCondition = lock.newCondition();
        pushQueueCondition = lock.newCondition();

        pullIndex = 0;
        pushIndex = 0;
        count = 0;
    }


    /**
     * 添加message到列表里；
     */
    public void enqueueMessage(Message message) {
        try {
            lock.lock();
            if (count == messages.length) {
                pushQueueCondition.await(); // 队列中的消息数量到达了上限之后应该停止添加；
            }
            messages[pushIndex] = message;
            pushIndex = (++pushIndex == messages.length ? 0 : pushIndex);
            count++;
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    /**
     * 取出列表中的消息；
     */
    public Message next() {
        Message message = null;
        try {
            lock.lock();
            if (count == 0) {
                pullQueueCondition.await();
            }
            message = messages[pullIndex];
            messages[pullIndex] = null;
            pullIndex = (++pullIndex == messages.length ? 0 : pullIndex);
            count--;
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        return message;
    }


}
