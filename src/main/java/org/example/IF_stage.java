package org.example;

public class IF_stage {
	
	//starting two versions of two variables: write and read address, write and read instruction code
	
	private int W_IncrPC, W_Instruction ;
	
	private int R_IncrPC, R_Instruction ;

	//set write version
	
	public void IF_write(int newAddress, int newInstruction) {
		
		this.W_IncrPC = newAddress;
		
		this.W_Instruction = newInstruction;
		
	}
	
	//copies from write to read
	
	public void Copy_write_to_read() {
		
		this.R_IncrPC = this.W_IncrPC;
		
		this.R_Instruction = this.W_Instruction;
	
	}
	
	//getter for address from read version
	
	public int getRIncrPC() {
		
        return R_IncrPC;
    }

	//getter for instruction from read version
	
    public int getRInstruction() {
    	
        return R_Instruction;
    }
    
    public int getWIncrPC() {
		
        return W_IncrPC;
    }

	//getter for instruction from read version
	
    public int getWInstruction() {
    	
        return W_Instruction;
    }
}
