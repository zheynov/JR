package com.javarush.test.level27.lesson09.home02;

public class MailServer implements Runnable {
    private final Mail mail;

    public MailServer(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        long beforeTime = System.currentTimeMillis();
        //сделайте что-то тут - do something here
        String name = Thread.currentThread().getName();
        synchronized (mail) {
            try
            {
                mail.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        long afterTime = System.currentTimeMillis();
        System.out.format("%s MailServer has got: [%s] in %d ms after start", name, mail.getText(), (afterTime - beforeTime));
    }
}
