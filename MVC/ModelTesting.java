public class ModelTesting {
    private Cell testCell;

    public ModelTesting(){
        testCell();
    }


    public void testCell(){
         testCell = new Cell();
         this.testCell.changeState();
         if (testCell.getState()) {
             System.out.println("This passed");
         }
    }

    public void testReset(){

    }

    public void testFillCell(){

    }

    public void testSimStep(){

    }
}

