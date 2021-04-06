public class ModelTesting {
    private Cell testCell;
    private Model testModel;


    public ModelTesting(){
        testCell();
        testReset();
        testSimStep();
    }


    public void testCell(){
         testCell = new Cell(0,0);
         this.testCell.changeState();
         if (testCell.getState()) {
             System.out.println("Cell test passed");
         }
    }

    public void testReset(){
        testModel = new Model();
        testModel.arr[1][2] = true;
        testModel.resetCells();
        if (!testModel.arr[1][2]){
            System.out.println("Reset test passed");
        }
    }

    public void testFillCell(){

    }

    public void testSimStep(){
        testModel = new Model();
        testModel.arr[1][1] = true;
        testModel.arr[1][2] = true;
        testModel.arr[2][1] = true;
        testModel.arr[2][2] = true;
        testModel.simStep();
        int counter = 0;
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 200; col++) {
                if (testModel.arr[row][col]){
                    counter++;
                }
            }
        }
        if (counter == 0){
            System.out.println(counter);
            System.out.println("simStep test passed");
        }
        else{
            System.out.println("simStep test failed");
        }
    }
}

