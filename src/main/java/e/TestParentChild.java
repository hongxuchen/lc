package e;

public class TestParentChild {

    public static void main(String[] args) {
        try {
            Child child = new Child();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Parent {
    public Parent() {
        System.out.println("parent ctor");
        toString();
    }

    @Override
    public String toString() {
        System.out.println("parent toString");
        return "Parent";
    }
}

class Child extends Parent {

    private String name;

    public Child() {
        super();
        System.out.println("child ctor");
        name = "child";
    }

    @Override
    public String toString() {
        System.out.println(name);
        return name.toUpperCase();
    }
}

