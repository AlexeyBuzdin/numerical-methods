package utils

class ConsoleInput implements DataInput {

    Scanner input = new Scanner(System.in);

    @Override
    double readNum(String variableName) {
        printf("Please enter %s value\n", variableName)
        return input.nextDouble()
    }
}
