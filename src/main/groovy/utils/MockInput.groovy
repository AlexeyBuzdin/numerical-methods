package utils

class MockInput implements DataInput {

    private Deque<Double> queue = new ArrayDeque<Double>()

    @Override
    public double readNum(String variableName) {
        return queue.isEmpty() ? 0 : queue.pollFirst()
    }

    public void addElements(double ... elements) {
        for (int i = 0; i < elements.length; i++) {
            queue.addLast(elements[i])
        }
    }
}
