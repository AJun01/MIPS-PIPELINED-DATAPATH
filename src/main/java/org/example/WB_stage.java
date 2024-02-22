package org.example;

public class WB_stage{
	
	private int W_MemtoReg, W_RegWrite;

	private int R_MemtoReg, R_RegWrite;
	
	private int W_ALUResult, W_WriteRegNum, W_LWDataValue;
	
	private int R_ALUResult, R_WriteRegNum, R_LWDataValue;
	
	private String W_opc, R_opc;
	
	private String W_Nfunc, R_Nfunc;
	
	private int W_Instruction, R_Instruction;

	  public void  Copy_write_to_read() {
		  
		  	this.R_MemtoReg = this.W_MemtoReg;
			
			this.R_RegWrite = this.W_RegWrite;
		  
		  	this.R_opc = this.W_opc;
		  	
		  	this.R_Nfunc = this.W_Nfunc;
			
			this.R_ALUResult = this.W_ALUResult;
			
			this.R_WriteRegNum = this.W_WriteRegNum;
			
			this.R_LWDataValue = this.W_LWDataValue;
			
			this.R_Instruction = this.W_Instruction;
			
		}
	  
	  public String toString() {
		  
		  String output = "";
		  
		 if (this.W_Instruction == 0) {

			 	output += "MEM/WB_Write (written to by the MEM stage)" + "\n" + "Control = 000000000" + "\n" + "\n";
				 
		  } else { 
			  
			  if(this.W_MemtoReg == 0 & this.W_RegWrite == 1) {
				    
				output += "MEM/WB_Write (written to by the MEM stage)" + "\n" + "Control: MemToReg=" + Integer.toHexString(getWMemtoReg()) + ", RegWrite=" + Integer.toHexString(getWRegWrite()) + ", [" + getW_NFunc() + "] " + "\n" + "LWDataValue = " + getWLWDataValue() + ", ALUResult = " + getWALUResult()+ ", WriteRegNum = " + getWWriteRegNum() + "\n"+ "\n";
		
			  } else {
			  
			  	output += "MEM/WB_Write (written to by the MEM stage)" + "\n" + "Control: MemToReg=" + Integer.toHexString(getWMemtoReg())  + ", RegWrite=" + Integer.toHexString(getWRegWrite()) + ", [" + getWOpc() + "] " + "\n" + "LWDataValue = " + getWLWDataValue() + ", ALUResult = " + getWALUResult()+ ", WriteRegNum = " + getWWriteRegNum() + "\n"+ "\n";
			  
			  }
		}
		  	  
			if (this.R_Instruction == 0) {
				
				output += "MEM/WB_Read (read by the WB stage)" + "\n" + "Control = 000000000" + "\n" + "\n";
			  
		  } else {
			  
			  if (this.R_MemtoReg == 0 & this.R_RegWrite == 1) {
				  			   
				output += "MEM/WB_Read (read by the MEM stage)" + "\n" + "Control: MemToReg=" + Integer.toHexString(getRMemtoReg()) + ", RegWrite=" + Integer.toHexString(getRRegWrite()) + ", [" + getR_NFunc() + "] " + "\n" + "LWDataValue = " + getRLWDataValue() + ", ALUResult = " + getRALUResult()+ ", WriteRegNum = " + getRWriteRegNum() + "\n"+ "\n";	

			  } else {
				   
				output += "MEM/WB_Read (read by the MEM stage)" + "\n" + "Control: MemToReg=" + Integer.toHexString(getRMemtoReg()) + ", RegWrite=" + Integer.toHexString(getRRegWrite()) + ", [" + getROpc() + "] " + "\n" + "LWDataValue = " + getRLWDataValue() + ", ALUResult = " + getRALUResult()+ ", WriteRegNum = " + getRWriteRegNum() + "\n"+ "\n";	
			  
			  }
					  
					  
		}
		  return output;
	  }
	  
	  //getters and setters for memToReg and RegWrite
	  
	  public int getWMemtoReg() {
			
			return this.W_MemtoReg;		
		}

		public int getWRegWrite() {
			
			return this.W_RegWrite;		
		}

		//getters for Read control signals 

		public int getRMemtoReg() {
			
			return this.R_MemtoReg;		
		}

		public int getRRegWrite() {
			
			return this.R_RegWrite;		
		}
		
		public void setMemtoReg(int newMemtoReg) {
			
			this.W_MemtoReg = newMemtoReg;		
		}

		public void setRegWrite(int newRegWrite) {
			
			this.W_RegWrite = newRegWrite;	
		}
		
		//getters and setters for opc, func, lwDataValue, ALUresult and writeRegNum
		
		public String getWOpc() {
			
			return this.W_opc;
	
		}
		
		public String getROpc() {
			
			return this.R_opc;
	
		}

		public String getW_NFunc() {
			
			return this.W_Nfunc;
		}

		public String getR_NFunc() {
			
			return this.R_Nfunc;
			
		}
		
		public int getRLWDataValue() {
			
			return this.R_LWDataValue;
		}

		public int getRWriteRegNum() {
			
			return this.R_WriteRegNum;
		}

		public int getRALUResult() {
			
			return this.R_ALUResult;
		}

		public int getWLWDataValue() {
			
			return this.W_LWDataValue;
		}

		public int getWWriteRegNum() {
			
			return this.W_WriteRegNum;
		}

		public int getWALUResult() {
			
			return this.W_ALUResult;
		}
		
		public int getWInstruction() {
			
			return this.W_Instruction;
		}


		public int getRInstruction() {
			
			return this.R_Instruction;
		}
		
		public void setOpc(String newOpc) {
			
			this.W_opc = newOpc;
		}
		
		public void setNFunc(String newFuncN) {
			
			this.W_Nfunc = newFuncN;
		}
		
		public void setWALUResult(int newALUResult) {
			
			this.W_ALUResult = newALUResult;
		}

		
		public void setLWDataValue(int newLWDataValue) {
			
			this.W_LWDataValue = newLWDataValue;
		}

		public void setWriteRegNum(int newWriteRegNum) {
			
			this.W_WriteRegNum = newWriteRegNum;
		}
   
		public void setInstruction(int newInstruction) {
			
			this.W_Instruction = newInstruction;
		}
		
}
