package org.example;

public class EX_stage{
	
	private int W_RegDst, W_ALUSrc, W_ALUOp, W_MemRead, W_MemWrite, W_Branch, W_MemtoReg, W_RegWrite;

	private int R_RegDst, R_ALUSrc, R_ALUOp, R_MemRead, R_MemWrite, R_Branch, R_MemtoReg, R_RegWrite;
	
	private int W_CalcBTA, W_ALUResult, W_SwValue, W_WriteRegNum = 0;
	
	private int R_CalcBTA, R_ALUResult, R_SwValue, R_WriteRegNum = 0;
	
	//start Write and Read version of data in ID stage
	private int W_IncrPC, W_ReadData1, W_ReadData2, W_SEOffset, W_Wreg_20_16, W_Wreg_15_11 = 0;

	private int R_IncrPC, R_ReadData1, R_ReadData2, R_SEOffset, R_Wreg_20_16, R_Wreg_15_11 = 0;
	
	private boolean W_Zero;
	
	private boolean R_Zero;
	
	private int W_Instruction, R_Instruction;
	
	private int W_func, R_func;
	
	private String W_Nfunc, R_Nfunc;
	
	private String W_opc, R_opc;

public void Copy_write_to_read() {	

	this.R_RegDst = this.W_RegDst;
	
	this.R_ALUSrc = this.W_ALUSrc;
	
	this.R_ALUOp = this.W_ALUOp;
	
	this.R_MemRead = this.W_MemRead;
	
	this.R_MemWrite = this.W_MemWrite;
	
	this.R_Branch = this.W_Branch;
	
	this.R_MemtoReg = this.W_MemtoReg;
	
	this.R_RegWrite = this.W_RegWrite;
	
	this.R_IncrPC = this.W_IncrPC;
	
	this.R_Instruction = this.W_Instruction;
	
	this.R_Nfunc = this.W_Nfunc;
	
	this.R_ReadData1 = this.W_ReadData1;
	
	this.R_ReadData2 = this.W_ReadData2;
	
	this.R_SEOffset = this.W_SEOffset;
	
	this.R_Wreg_20_16 = this.W_Wreg_20_16;
	
	this.R_Wreg_15_11 = this.W_Wreg_15_11;
	
	this.R_CalcBTA = this.W_CalcBTA;
	
	this.R_ALUResult = this.W_ALUResult;
	
	this.R_SwValue = this.W_SwValue;
	
	this.R_WriteRegNum = this.W_WriteRegNum;
	
	this.R_opc = this.W_opc;
	
	this.R_func = this.W_func;
	}
		
	public String toString() {
		
		String output = "";
		
	if (this.W_Instruction==0) {
			
			output += "ID/EX_Write (written to by the ID stage)" + "\n" + "Control = 000000000" + "\n" + "\n";
		
		} else {	
			
			if (this.W_ALUOp == 0b10 & this.W_RegDst == 0b1) {
				
				output += "ID/EX_Write (written to by the ID stage)" + "\n" + "Control: RegDst=" + Integer.toHexString(getWRegDst()) + ", ALUSrc=" + Integer.toHexString(getWALUSrc()) + ", ALUOp=" +Integer.toHexString(getWALUOp()) + ", MemRead=" + Integer.toHexString(getWMemRead()) +  ", MemWrite=" + Integer.toHexString(getWMemWrite())

				+ ", Branch=" + Integer.toHexString(getWBranch()) + ", MemToReg=" + Integer.toHexString(getWMemtoReg()) + ", [" + getW_NFunc() + "] " + "\n" + "IncrPC = " + Integer.toHexString(getWIncrPC()) + ", ReadReg1Value = " + Integer.toHexString(getWReadData1()) + ", ReadReg2Value = " + Integer.toHexString(getWReadData2()) + "\n" 

				+ "SEOffset = " + getWSEOffset() + ", WriteReg_20_16 = " + getWWreg_20_16() + ", WriteReg_15_11 = " + getWWreg_15_11() + ", Function = " + Integer.toHexString(getW_Func()) + "\n" + "\n";
				
			} else {
			
				output += "ID/EX_Write (written to by the ID stage)" + "\n" + "Control: RegDst=" + Integer.toHexString(getWRegDst()) + ", ALUSrc=" + Integer.toHexString(getWALUSrc()) + ", ALUOp=" +Integer.toHexString(getWALUOp()) + ", MemRead=" + Integer.toHexString(getWMemRead()) +  ", MemWrite=" + Integer.toHexString(getWMemWrite())

				+ ", Branch=" + Integer.toHexString(getWBranch()) + ", MemToReg=" + Integer.toHexString(getWMemtoReg()) + ", [" + getW_Opc() + "] " + "\n" + "IncrPC = " + Integer.toHexString(getWIncrPC()) + ", ReadReg1Value = " + Integer.toHexString(getWReadData1()) + ", ReadReg2Value = " + Integer.toHexString(getWReadData2()) + "\n" 

			+ "SEOffset = " + Integer.toHexString(getWSEOffset()) + ", WriteReg_20_16 = " + getWWreg_20_16() + ", WriteReg_15_11 = " + getWWreg_15_11() + ", Function = X" + "\n" + "\n";
		
			}
	}	

	if (this.R_Instruction==0) {

			output +=  "ID/EX_Read (read by the EX stage)" + "\n" + "Control = 000000000" + "\n" + "\n";
		
		} else {
			
			if (this.R_ALUOp == 0b10 & this.R_RegDst == 0b1) {
				
				output += "ID/EX_Read (read by the EX stage)" + "\n" + "Control: RegDst=" + Integer.toHexString(getRRegDst()) + ", ALUSrc=" + Integer.toHexString(getRALUSrc()) + ", ALUOp=" + Integer.toHexString(getRALUOp()) + ", MemRead=" + Integer.toHexString(getRMemRead()) +  ", MemWrite=" + Integer.toHexString(getRMemWrite())

				+ ", Branch=" + Integer.toHexString(getRBranch()) + ", MemToReg=" + Integer.toHexString(getRMemtoReg()) + ", [" + getR_NFunc() + "] " +"\n" + "IncrPC = " + Integer.toHexString(getRIncrPC()) + ", ReadReg1Value = " + Integer.toHexString(getRReadData1()) + ", ReadReg2Value = " + Integer.toHexString(getRReadData2()) + "\n" 

				+ "SEOffset = " + getRSEOffset() + ", WriteReg_20_16 = " + getRWreg_20_16() + ", WriteReg_15_11 = " + getRWreg_15_11() + ", Function = " + Integer.toHexString(getR_Func()) + "\n" + "\n";
				
			} else {

				output += "ID/EX_Read (read by the EX stage)" + "\n" + "Control: RegDst=" + Integer.toHexString(getRRegDst()) + ", ALUSrc=" + Integer.toHexString(getRALUSrc()) + ", ALUOp=" + Integer.toHexString(getRALUOp()) + ", MemRead=" + Integer.toHexString(getRMemRead()) +  ", MemWrite=" + Integer.toHexString(getRMemWrite())

				+ ", Branch=" + Integer.toHexString(getRBranch()) + ", MemToReg=" + Integer.toHexString(getRMemtoReg()) + ", [" + getR_Opc() + "] " + "\n" + "IncrPC = " + Integer.toHexString(getRIncrPC()) + ", ReadReg1Value = " + Integer.toHexString(getRReadData1()) + ", ReadReg2Value = " + Integer.toHexString(getRReadData2()) + "\n" 

			+ "SEOffset = " + Integer.toHexString(getRSEOffset()) + ", WriteReg_20_16 = " + getRWreg_20_16() + ", WriteReg_15_11 = " + getRWreg_15_11() + ", Function = X" + "\n" + "\n";
			
			}
		}
		
		return output;
	}
	

	
	//getters for Write control signals 

	public int getWRegDst() {
		
		return this.W_RegDst;
	}

	public int getWALUSrc() {
		
		return this.W_ALUSrc;		
	}

	public int getWALUOp() {
		
		return this.W_ALUOp;		
	}

	public int getWMemRead() {
		
		return this.W_MemRead;		
	}

	public int getWMemWrite() {
		
		return this.W_MemWrite;		
	}

	public int getWBranch() {
		
		return this.W_Branch;	
	}

	public int getWMemtoReg() {
		
		return this.W_MemtoReg;		
	}

	public int getWRegWrite() {
		
		return this.W_RegWrite;		
	}

	//getters for Read control signals 

	public int getRRegDst() {
		
		return this.R_RegDst;
	}

	public int getRALUSrc() {
		
		return this.R_ALUSrc;		
	}

	public int getRALUOp() {
		
		return this.R_ALUOp;		
	}

	public int getRMemRead() {
		
		return this.R_MemRead;		
	}

	public int getRMemWrite() {
		
		return this.R_MemWrite;		
	}

	public int getRBranch() {
		
		return this.R_Branch;	
	}

	public int getRMemtoReg() {
		
		return this.R_MemtoReg;		
	}

	public int getRRegWrite() {
		
		return this.R_RegWrite;		
	}
//getters for writes

public int getRCalcBTA() {
	
	return this.R_CalcBTA;
}

public boolean getRZero() {
	
	return this.R_Zero;
}

public int getRSwValue() {
	
	return this.R_SwValue;
}

public int getRALUResult() {
	
	return this.R_ALUResult;
}

public int getRWriteRegNum() {
	
	return this.R_WriteRegNum;		
}


public int getWCalcBTA() {
	
	return this.W_CalcBTA;
}

public boolean getWZero() {
	
	return this.W_Zero;
}

public int getWSwValue() {
	
	return this.W_SwValue;
}

public int getWALUResult() {
	
	return this.W_ALUResult;
}

public int getWWriteRegNum() {
	
	return this.W_WriteRegNum;		
}
//getters for func and opc

public int getW_Func() {
	
	return this.W_func;
}

public int getR_Func() {
	
	return this.R_func;
	
}

public String getW_NFunc() {
	
	return this.W_Nfunc;
}

public String getR_NFunc() {
	
	return this.R_Nfunc;
	
}

public String getW_Opc() {
	
	return this.W_opc;
}

public String getR_Opc() {
	
	return this.R_opc;
}

//getter and setter for things from last stage

public int getRIncrPC() {
	
	return this.R_IncrPC;		
}

public int getRReadData1() {
	
	return this.R_ReadData1;		
}

public int getRReadData2() {

	return this.R_ReadData2;		
}

public int getRSEOffset() {
	
	return this.R_SEOffset;		
}

public int getRWreg_20_16() {
	
	return this.R_Wreg_20_16;
}

public int getRWreg_15_11() {
	
	return this.R_Wreg_15_11;
}

public int getWIncrPC() {
	
	return this.W_IncrPC;		
}

public int getWReadData1() {
	
	return this.W_ReadData1;		
}

public int getWReadData2() {

	return this.W_ReadData2;		
}

public int getWSEOffset() {
	
	return this.W_SEOffset;		
}

public int getWWreg_20_16() {
	
	return this.W_Wreg_20_16;
}

public int getWWreg_15_11() {
	
	return this.W_Wreg_15_11;
}

public int getWInstruction() {
	
	return this.W_Instruction;
}


public int getRInstruction() {
	
	return this.R_Instruction;
}

public void setWIncrPC(int newIncrPC) {
	
	this.W_IncrPC = newIncrPC;		
}

public void setWReadData1(int newReadData1) {
	
	this.W_ReadData1 = newReadData1;		
}

public void setWReadData2(int newReadData2) {

	this.W_ReadData2 = newReadData2;		
}

public void setWSEOffset(int newSEOffset) {
	
	this.W_SEOffset = newSEOffset;		
}

public void setWWreg_20_16(int newWreg_20_16) {
	
	this.W_Wreg_20_16 = newWreg_20_16;
}

public void setWWreg_15_11(int newWreg_15_11) {
	
	this.W_Wreg_15_11 = newWreg_15_11;
}
	
	//setters for calcBTA, zero, writeRegNum, ALUResult
	public void setWCalcBTA(int newCalcBTA) {
		
		W_CalcBTA = newCalcBTA;
	}
	
	public void setWZero(boolean newZero) {
		
		this.W_Zero = newZero;
	}
	
	public void setWWriteRegNum(int newWriteRegNum) {
		
		this.W_WriteRegNum = newWriteRegNum;
	}
	
	public void setWALUResult(int newALUResult ) {
		
		this.W_ALUResult = newALUResult;
	
	}
	
	public void setWSwValue(int newSwValue) {
		
		this.W_SwValue = newSwValue;
	}
	
	//setters for signals 
	
	public void setRegDst(int newRegDst) {
		
		 this.W_RegDst = newRegDst;
	}

	public void setALUSrc(int newALUSrc) {
		
		this.W_ALUSrc = newALUSrc;		
	}

	public void setALUOp(int newALUSrc) {
		
	    this.W_ALUOp = newALUSrc;		
	}

	public void setMemRead(int newMemRead) {
		
		this.W_MemRead = newMemRead;		
	}

	public void setMemWrite(int newMemWrite) {
		
		this.W_MemWrite = newMemWrite;		
	}

	public void setBranch(int newBranch) {
		
		this.W_Branch = newBranch;	
	}

	public void setMemtoReg(int newMemtoReg) {
		
		this.W_MemtoReg = newMemtoReg;		
	}

	public void setRegWrite(int newRegWrite) {
		
		this.W_RegWrite = newRegWrite;		
	}
	
	public void setFunc(int newFunc) {
		
		this.W_func = newFunc;
	}
	
	public void setNFunc(String newFuncN) {
		
		this.W_Nfunc = newFuncN;
	}

	public void setWOpc(String newOpc) {
		
		this.W_opc = newOpc;
	}
	
	public void setInstruction(int newInstruction) {
		
		this.W_Instruction = newInstruction;
	}
	
}
