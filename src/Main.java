public class Main {

    public static void main(String[] args) {
        MyArrayListObject objects = new MyArrayListObject();

        objects.add("Java");
        objects.add("Data");
        objects.add("Structures");

        String text = (String) objects.get(0);

    }
}
