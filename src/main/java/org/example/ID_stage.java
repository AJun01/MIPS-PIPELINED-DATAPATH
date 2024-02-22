package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ID_stage{
	
	//start control signals 
private int W_RegDst, W_ALUSrc, W_ALUOp, W_MemRead, W_MemWrite, W_Branch, W_MemtoReg, W_RegWrite = 0b0;

private int R_RegDst, R_ALUSrc, R_ALUOp, R_MemRead, R_MemWrite, R_Branch, R_MemtoReg, R_RegWrite = 0b0;

//start Write and Read version of data in ID stage
private int W_IncrPC, W_ReadData1, W_ReadData2, W_SEOffset, W_Wreg_20_16, W_Wreg_15_11 = 0;

private int R_IncrPC, R_ReadData1, R_ReadData2, R_SEOffset, R_Wreg_20_16, R_Wreg_15_11 = 0;

private int W_Instruction, R_Instruction;

private int W_func, R_func;

private String W_opc, R_opc;


public void Copy_write_to_read() {	
	
	//signal
	
	this.R_RegDst = this.W_RegDst;
	
	this.R_ALUSrc = this.W_ALUSrc;
	
	this.R_ALUOp = this.W_ALUOp;
	
	this.R_MemRead = this.W_MemRead;
	
	this.R_MemWrite = this.W_MemWrite;
	
	this.R_Branch = this.W_Branch;
	
	this.R_MemtoReg = this.W_MemtoReg;
	
	this.R_RegWrite = this.W_RegWrite;
	
	//data
	
	this.R_IncrPC = this.W_IncrPC;
	
	this.R_Instruction = this.W_Instruction;
	
	this.R_ReadData1 = this.W_ReadData1;
	
	this.R_ReadData2 = this.W_ReadData2;
	
	this.R_SEOffset = this.W_SEOffset;
	
	this.R_Wreg_20_16 = this.W_Wreg_20_16;
	
	this.R_Wreg_15_11 = this.W_Wreg_15_11;
	
	//opc and func
	
	this.R_opc = this.W_opc;
	
	this.R_func = this.W_func;
}

//decoding hashmap for store/load and add/subtract

public  Map<Integer, String> opCodes;

   {
	  
    Map<Integer, String> tempMap = new HashMap<Integer, String>();
    
    tempMap.put(0x20, "lb");
    
    tempMap.put(0x28, "sb");
    
    opCodes = Collections.unmodifiableMap(tempMap);
    
  }
   
public  Map<Integer, String> funcCodes;

  {
	  
    Map<Integer, String> tempMap = new HashMap<Integer, String>();
    
    tempMap.put(0x20, "add");
    
    tempMap.put(0x22, "sub");
    
    funcCodes = Collections.unmodifiableMap(tempMap);
    
  }
  
public String getRegNum(int reg) {
		
	    return '$' + Integer.toString(reg);
	    
	  }

//masking methods for register and destination : Reg1(25-21), Reg2(20-16) and Des(15-11)

public int getReg1(int address) {  
	
	int reg1 = (address & 0x3E00000) >>> 21;
	
	return reg1;
}

public int getReg2(int address) {
	
	int reg2 = (address & 0x1F0000) >>> 16;
	
	return reg2;
}

public int RgetDes(int address) {
	
	int des = (address & 0xF800) >>> 11;
	
	return des;
}

//masking method for function codes

public String RgetFunc(int address) {
	
	int func = (address & 0x3F);
	
	return funcCodes.get(func);
	}

//masking method for operation codes

public String IgetOpc(int address) {
	
	int opc = (address & 0xFC000000) >>> 26;
	
	return opCodes.get(opc);
}

//masking method for offsets

public int IgetOffset(int address) {
	
	short offset = (short)(address & 0xFFFF);
	
	return offset;
}

//decoder

public String decoder(int address) {
	
	if ((address & 0xFC000000) == 0) {
		
		return(RgetFunc(address) + getRegNum(RgetDes(address)) + ", " +  getRegNum(getReg1(address)) + ", " + getRegNum(getReg2(address)));
	
	} else {
			
			return(IgetOpc(address) + " " + getReg2(address) + ", " + IgetOffset(address) + "(" + getReg1(address) + ") ");
		}			
	}	



public String toString() {
	
	String output = "";
	
	if (this.W_Instruction==0) {

		output +=  "IF/ID_Write (written to by the IF stage)" + "\n" + "Inst = 000000000" + "\n" + "\n";
	
	} else {	

		output +=  "IF/ID_Write (written to by the IF stage)" + "\n" + "Inst = 0x" + Integer.toHexString(this.W_Instruction) + " [ " + decoder(this.W_Instruction) + " ] " + "IncrPC = 0x" + Integer.toHexString(getWIncrPC()) + "\n" + "\n";
	
} 

	if (this.R_Instruction == 0) {

		output +=  "IF/ID_Read (read by the ID stage)" + "\n" + "Inst = 000000000" + "\n" + "\n";

	} else {			
	
		output +=  "IF/ID_Read (read by the ID stage)" + "\n" + "Inst = 0x" + Integer.toHexString(this.R_Instruction) + " [ " + decoder(this.R_Instruction) + " ] " + "IncrPC = 0x" + Integer.toHexString(getRIncrPC()) + "\n" + "\n";
		
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
//getters for data in ID stage

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
//getters for func or opt

public int getW_Func() {
	
	return this.W_func;
}

public int getR_Func() {
	
	return this.R_func;
	
}
	
public String getW_Opc() {
	
	return this.W_opc;
}

public String getR_Opc() {
	
	return this.R_opc;
}

public int getWInstruction() {
	
	return this.W_Instruction;
}


public int getRInstruction() {
	
	return this.R_Instruction;
}

	//setters for everything
public void setInstruction(int newInstruction) {
	
	this.W_Instruction = newInstruction;
}

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

//setters for Writes

public void setIncrPC(int newIncrPC) {
	
	this.W_IncrPC = newIncrPC;		
}

public void setReadData1(int newReadData1) {
	
	this.W_ReadData1 = newReadData1;		
}

public void setReadData2(int newReadData2) {

	this.W_ReadData2 = newReadData2;		
}

public void setSEOffset(int newSEOffset) {
	
	this.W_SEOffset = newSEOffset;		
}

public void setWreg_20_16(int newWreg_20_16) {
	
	this.W_Wreg_20_16 = newWreg_20_16;
}

public void setWreg_15_11(int newWreg_15_11) {
	
	this.W_Wreg_15_11 = newWreg_15_11;
}

public void setFunc(int newFunc) {
	
	this.W_func = newFunc;
}

public void setOpc(String newOpc) {
	
	this.W_opc = newOpc;
}
}
