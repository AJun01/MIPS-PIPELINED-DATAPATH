package org.example;

public class MEM_stage{
	
	private int W_RegDst, W_ALUSrc, W_ALUOp, W_MemRead, W_MemWrite, W_Branch, W_MemtoReg, W_RegWrite;

	private int R_RegDst, R_ALUSrc, R_ALUOp, R_MemRead, R_MemWrite, R_Branch, R_MemtoReg, R_RegWrite;
	
	private int W_ALUResult, W_WriteRegNum, W_LWDataValue;
	
	private int R_ALUResult, R_WriteRegNum, R_LWDataValue;
	
	private int W_CalcBTA, W_SwValue = 0;
	
	private int R_CalcBTA, R_SwValue = 0;
	
	private int W_Instruction, R_Instruction;
	
	private boolean W_Zero;
	
	private boolean R_Zero;
		
	private String W_opc, R_opc;
	
	private String W_Nfunc, R_Nfunc;

public void  Copy_write_to_read() {
	
	this.R_RegDst = this.W_RegDst;
	
	this.R_ALUSrc = this.W_ALUSrc;
	
	this.R_ALUOp = this.W_ALUOp;
	
	this.R_MemRead = this.W_MemRead;
	
	this.R_MemWrite = this.W_MemWrite;
	
	this.R_Branch = this.W_Branch;
	
	this.R_MemtoReg = this.W_MemtoReg;
	
	this.R_RegWrite = this.W_RegWrite;
	
	this.R_opc = this.W_opc;
	
	this.R_Nfunc = this.W_Nfunc;
	
	this.R_Zero = this.W_Zero;
	
	this.R_ALUResult = this.W_ALUResult;
	
	this.R_WriteRegNum = this.W_WriteRegNum;
	
	this.R_LWDataValue = this.W_LWDataValue;
	
	this.R_CalcBTA = this.W_CalcBTA;
	
	this.R_SwValue = this.W_SwValue;
	
	this.R_Instruction = this.W_Instruction;
	
	
}


public String toString() {
	
	String output = "";
	
	if (this.W_Instruction==0) {
		
		output += "EX/MEM_Write (written to by the EX stage)" + "\n" + "Control = 000000000" + "\n" + "\n";
	
	} else {
		
		if (this.W_ALUOp == 0b10 & this.W_RegDst == 0b1) {
			
		output +=  "EX/MEM_Write (written to by the EX stage)" + "\n" + "Control: MemRead=" + Integer.toHexString(getWMemRead()) +  ", MemWrite=" + Integer.toHexString(getWMemWrite()) + ", Branch=" + Integer.toHexString(getWBranch()) + ", MemToReg=" + Integer.toHexString(getWMemtoReg()) + ", RegWrite=" + Integer.toHexString(getWRegWrite()) + ", [" + getW_NFunc() + "] " + "\n"

		+ "CalcBTA = " + getWCalcBTA() + ", Zero = " + getWZero() + ", ALUResult = " + getWALUResult() + "\n" + "SWValue = " + Integer.toHexString(getWSwValue()) + ", WriteRegNUm =  " + getWWriteRegNum() + "\n" + "\n";
		
			
		} else {
	
			output +=  "EX/MEM_Write (written to by the EX stage)" + "\n" + "Control: MemRead=" + Integer.toHexString(getWMemRead()) +  ", MemWrite=" + Integer.toHexString(getWMemWrite()) + ", Branch=" + Integer.toHexString(getWBranch()) + ", MemToReg=" + Integer.toHexString(getWMemtoReg()) + ", RegWrite=" + Integer.toHexString(getWRegWrite()) + ", [" + getW_Opc() + "] " + "\n"

		+ "CalcBTA = " + getWCalcBTA() + ", Zero = " + getWZero() + ", ALUResult = " + getWALUResult() + "\n" + "SWValue = " + Integer.toHexString(getWSwValue()) + ", WriteRegNUm =  " + getWWriteRegNum() + "\n" + "\n";
		
		}
}
	
	if (this.R_Instruction==0) {
	
		output +=  "EX/MEM_Read (read by the MEM stage)" + "\n" + "Control = 000000000" + "\n" + "\n";
		
	} else {				
		
		if (this.R_ALUOp == 0b10 & this.R_RegDst == 0b1) {
			
			output +=  "EX/MEM_Read (read by the MEM stage)" + "\n" + "Control: MemRead=" + Integer.toHexString(getRMemRead()) +  ", MemWrite=" + Integer.toHexString(getRMemWrite()) + ", Branch=" + Integer.toHexString(getRBranch()) + ", MemToReg=" + Integer.toHexString(getRMemtoReg()) + ", RegWrite=" + Integer.toHexString(getRRegWrite()) + ", [" + getR_NFunc() + "] " + "\n"

			+ "CalcBTA = " + getRCalcBTA() + ", Zero = " + getRZero() + ", ALUResult = " + getRALUResult() + "\n" + "SWValue = " + Integer.toHexString(getRSwValue()) +", WriteRegNUm =  " + getRWriteRegNum() + "\n" + "\n";	

			
		} else {
			
			output +=  "EX/MEM_Read (read by the MEM stage)" + "\n" + "Control: MemRead=" + Integer.toHexString(getRMemRead()) +  ", MemWrite=" + Integer.toHexString(getRMemWrite()) + ", Branch=" + Integer.toHexString(getRBranch()) + ", MemToReg=" + Integer.toHexString(getRMemtoReg()) + ", RegWrite=" + Integer.toHexString(getRRegWrite()) + ", [" + getR_Opc() + "] " + "\n"

			+ "CalcBTA = " + getRCalcBTA() + ", Zero = " + getRZero() + ", ALUResult = " + getRALUResult() + "\n" + "SWValue = " + Integer.toHexString(getRSwValue()) +", WriteRegNUm =  " + getRWriteRegNum() + "\n" + "\n";	

			
		}

}
	return output;
}
//getter for LWDatavalue and WriteRegNum and ALUResult
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

//setters for LWDatavalue and WriteRegNum and ALUResult
public void setLWDataValue(int newLWDataValue) {
	
	this.W_LWDataValue = newLWDataValue;
}

public void setWriteRegNum(int newWriteRegNum) {
	
	this.W_WriteRegNum = newWriteRegNum;
}

public void setWALUResult(int newALUResult) {
	
	this.W_ALUResult = newALUResult;
}


//setters and getters for another three 
public int getWCalcBTA() {
	
	return this.W_CalcBTA;
}

public boolean getWZero() {
	
	return this.W_Zero;
}

public int getWSwValue() {
	
	return this.W_SwValue;
}

public int getRCalcBTA() {
	
	return this.R_CalcBTA;
}

public boolean getRZero() {
	
	return this.R_Zero;
}

public int getRSwValue() {
	
	return this.R_SwValue;
}

public void setWCalcBTA(int newCalcBTA) {
	
	W_CalcBTA = newCalcBTA;
}

public void setWZero(boolean newZero) {
	
	this.W_Zero = newZero;
}

public void setWSwValue(int newSwValue) {
	
	this.W_SwValue = newSwValue;
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
//getter for opc and func
	
	public String getW_Opc() {
		
		return this.W_opc;
	}

	public String getR_Opc() {
		
		return this.R_opc;
	}
	
	public String getW_NFunc() {
		
		return this.W_Nfunc;
	}

	public String getR_NFunc() {
		
		return this.R_Nfunc;
		
	}
	
	public int getWInstruction() {
		
		return this.W_Instruction;
	}


	public int getRInstruction() {
		
		return this.R_Instruction;
	}

//setters for opc and func
	
	public void setOpc(String newOpc) {
		
		this.W_opc = newOpc;
	}
	
	public void setNFunc(String newFuncN) {
		
		this.W_Nfunc = newFuncN;
	}
	
	public void setInstruction(int newInstruction) {
		
		this.W_Instruction = newInstruction;
	}
	
	
}
