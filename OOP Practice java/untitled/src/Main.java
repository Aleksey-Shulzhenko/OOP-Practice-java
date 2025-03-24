public class Main
{
    public static void main(String[] args)
    {
        String[] predefinedArgs = {"Hello", "World", "123"};

        System.out.println("Аргументи командного рядка:");
        for (int i = 0; i < predefinedArgs.length; i++) {
            System.out.println("Аргумент " + (i + 1) + ": " + predefinedArgs[i]);
        }
    }
}
