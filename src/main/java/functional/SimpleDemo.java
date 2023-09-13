package functional;

public class SimpleDemo {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SimpleDemo demo = new SimpleDemo();
        demo.run();
    }
    public void run(){
        // ex1
        System.out.println("ex1");
        String[] names = {"Alice", "Bob", "Charlie", "David"};
        String[] filteredNames = filter(names, new FilterByMinLength5());
        for (String name : filteredNames) {
            if (name != null) {
                System.out.println(name);
            }
        }
        // ex2
        System.out.println("ex2");
        String[] filteredNames2 = filter(names, new Filter() {
            @Override
            public boolean accept(String name) {
                return name.length() >= 5;
            }
        });
        for (String name : filteredNames2) {
            if (name != null) {
                System.out.println(name);
            }
        }
        // ex3: Lambda
        System.out.println("ex3");
        String[] filteredNames3 = filter(names, (String name) -> name.length() >= 5);
        for (String name : filteredNames3) {
            if (name != null) {
                System.out.println(name);
            }
        }
        // ex4
        System.out.println("ex4");
        String[] filteredNames4 = filter(names, name -> name.length() >= 5);
        for (String name : filteredNames4) {
            if (name != null) {
                System.out.println(name);
            }
        }
    }
    private interface Filter {
        boolean accept(String name);
    }
    private class FilterByMinLength5 implements Filter {
        @Override
        public boolean accept(String name) {
            return name.length() >= 5;
        }
    }
    private String[] filter(String[] names, Filter filter) {
        String[] result = new String[names.length];
        int index = 0;
        for (String name : names) {
            if (filter.accept(name)) {
                result[index++] = name;
            }
        }
        return result;
    }
}
