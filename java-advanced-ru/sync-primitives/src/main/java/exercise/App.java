package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        SafetyList list = new SafetyList();
        Thread thread = new Thread(new ListThread(list));
        Thread thread2 = new Thread(new ListThread(list));
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(list.getSize());
        // END
    }
}

