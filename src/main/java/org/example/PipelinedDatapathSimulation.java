package org.example;

/*
 * This Java program simulates a pipelined datapath,
 * a fundamental concept in computer architecture that
 * enhances the performance of a CPU by executing multiple
 * instructions simultaneously, each at different stages of
 * completion. The simulation mimics the behavior of a MIPS
 * (Microprocessor without Interlocked Pipelined Stages)
 * processor's pipeline, which typically includes the following
 * stages:
 * Instruction Fetch (IF), Instruction Decode (ID), Execute (EX), Memory Access (MEM), and Write Back (WB).
 */
public class PipelinedDatapathSimulation {
	

	
	public void proceed() {
		
		int[] Regs = new int[32];
		
		Regs[0] = 0;
		
		int regStartVal = 0x100;
		
		for (int i = 1; i < Regs.length; i++) {
			
			Regs[i] = regStartVal+i;
		}
		
		int[] Main_Mem = new int[1024];
		
		int memStartVal = 0;
		
		for (int i = 0; i < Main_Mem.length; i++) {
			
			Main_Mem[i] = memStartVal;
			
			memStartVal++;
			
			if (memStartVal > 255) {
				
				memStartVal = 0;
			}
		}
		
	    IF_stage f = new IF_stage();
		
		ID_stage d = new ID_stage();
		
		EX_stage x = new EX_stage();

		MEM_stage m = new MEM_stage();
		
		WB_stage b = new WB_stage();
		
		int[] test = {0xa1020000
					,0x810AFFFC
					,0x00831820
					,0x01263820
					,0x01224820
					,0x81180000
					,0x81510010
					,0x00624022
					,0x00000000 //# This is a nop, used just to allow the "real" instructions finish in the pipeline
					,0x00000000 //# This is a nop, used just to allow the "real" instructions finish in the pipeline
					,0x00000000// # This is a nop, used just to allow the "real" instructions finish in the pipeline
					,0x00000000 };
	 
	for (int t = 0; t < test.length; t++) {
		
		System.out.println("Clock Cycle " + t + "(before copy)" +  "\n");
		
		IF_stage(regStartVal + t, test[t], f);
		
		ID_stage(f, d, Regs);
		
		EX_stage(x ,d);
		
		MEM_stage(m, x, Main_Mem);
		
		WB_stage(b, m, Regs);
		
		Print_out_everything(d, x, m ,b ,Regs);
		
		Copy_write_to_read(f, d, x, m ,b);
		
		System.out.println("Clock Cycle " + t + "(after copy)" + "\n");
		
		Print_out_everything(d, x, m ,b, Regs);
		
	}
	}
	
	public void IF_stage(int address, int instruction, IF_stage f) {
		
		f.IF_write(address, instruction);
		
	}
	
	public void ID_stage(IF_stage f, ID_stage d ,int[] Regs) {
		
		d.setIncrPC(f.getRIncrPC());
		
		d.setInstruction(f.getRInstruction());
		
		int Instruction = d.getWInstruction();
		
		if ((Instruction & 0xFC000000) == 0) {
			
			if(d.RgetFunc(Instruction) == "add") {
				
				d.setFunc(0x20);
				
				d.setRegDst(0b1);
				
				d.setALUSrc(0b0);
				
				d.setALUOp(0b10);
				
				d.setMemRead(0b0);
				
				d.setMemWrite(0b0);
				
				d.setBranch(0b0);
				
				d.setMemtoReg(0b0);
				
				d.setRegWrite(0b1);
			
				d.setSEOffset(d.IgetOffset(Instruction)); 
				
			} else if(d.RgetFunc(Instruction) == "sub") {
				
				d.setFunc(0x22);
				
				d.setRegDst(0b1);
				
				d.setALUSrc(0b0);
				
				d.setALUOp(0b10);
				
				d.setMemRead(0b0);
				
				d.setMemWrite(0b0);
				
				d.setBranch(0b0);
				
				d.setMemtoReg(0b0);
				
				d.setRegWrite(0b1);
			
				d.setSEOffset(d.IgetOffset(Instruction)); 
				
			} else {
				
				System.out.print("this is a opc in R-format");
			}
					
		} else {
			
			if ((Instruction & 0xFC000000) != 0 & d.IgetOpc(Instruction) == "lb" ) {
						
				d.setOpc("lb");
				
				d.setRegDst(0b0);
				
				d.setALUSrc(0b1);
				
				d.setALUOp(0b00);
				
				d.setMemRead(0b1);
				
				d.setMemWrite(0b0);
				
				d.setBranch(0b0);
				
				d.setMemtoReg(0b1);
				
				d.setRegWrite(0b1);
				
				d.setSEOffset(d.IgetOffset(Instruction) << 4); 
				
			} else if((Instruction & 0xFC000000) != 0 & d.IgetOpc(Instruction) == "sb") {
			
				d.setOpc("sb");
				
				d.setRegDst(0b0);
			
				d.setALUSrc(0b1);
			
				d.setALUOp(0b00);
			
				d.setMemRead(0b1);
				
				d.setMemWrite(0b0);
				
				d.setBranch(0b0);
				
				d.setMemtoReg(0b0);
				
				d.setRegWrite(0b0);
			
				d.setSEOffset(d.IgetOffset(Instruction) << 4);
			
			} 
			}
		
		d.setReadData1(Regs[d.getReg1(Instruction)]); 
		
		d.setReadData2(Regs[d.getReg2(Instruction)]); 
		
		d.setWreg_15_11(d.RgetDes(Instruction));
		
		d.setWreg_20_16(d.getReg2(Instruction));
	}
	
	public void EX_stage(EX_stage x, ID_stage d) {
		
		x.setInstruction(d.getRInstruction());
		
		//passing down to ex from id for toString
		
		x.setWIncrPC(d.getRIncrPC());
		
		x.setWSEOffset(d.getRSEOffset());
		
		x.setWReadData1(d.getRReadData1()); 
		
		x.setWReadData2(d.getRReadData2()); 
		
		x.setWWreg_15_11(d.getRWreg_15_11());
		
		x.setWWreg_20_16(d.getRWreg_20_16());
		
		//passing down for calculation
		
		x.setRegDst(d.getRRegDst());
		
		x.setALUSrc(d.getRALUSrc());
		
		x.setALUOp(d.getRALUOp());
		
		x.setMemRead(d.getRMemRead());

		x.setMemWrite(d.getRMemWrite());

		x.setBranch(d.getRBranch()); 

		x.setMemtoReg(d.getRMemtoReg()); 

		x.setRegWrite(d.getRRegWrite());
		

		x.setFunc(d.getR_Func());
		
		x.setNFunc(d.RgetFunc(d.getRInstruction()));

		x.setWOpc(d.getR_Opc());
			
		x.setRegDst(d.getRRegDst()); 
		
		
		x.setALUSrc(d.getRALUSrc()); 
		
		x.setALUOp(d.getRALUOp());
		
		x.setWCalcBTA(d.getRIncrPC() + 1);
			
		x.setWSwValue(d.getRReadData2());
		
		
		int MUX1Result;
		
		if (x.getWALUSrc() == 0b0 ) {
			
			MUX1Result = x.getWReadData2();
			
		} else {
			
			MUX1Result = x.getWSEOffset();
		}
		
		if (x.getWALUOp() == 0b10) {
			
			if(x.getWReadData1() - MUX1Result == 0) {
				
					x.setWZero(true);
				
					x.setWALUResult(0); 
				
			} else {
				
					x.setWZero(false); 
				
					x.setWALUResult(x.getWReadData1() - MUX1Result);
			} 
			
			} 
		else if(x.getWALUOp() == 0b00) {
			
				if(x.getWReadData1() + MUX1Result == 0) {
				
					x.setWZero(true);
				
					x.setWALUResult(0);
					
				} else {
				
					x.setWZero(false);
				
					x.setWALUResult(x.getWReadData1() + MUX1Result);
				} 
			}
		
		if (x.getWRegDst() == 0b0 ) {
			
			 x.setWWriteRegNum(x.getWWreg_20_16());
			
		} else {
			
			 x.setWWriteRegNum(x.getWWreg_15_11());
		}
		}
	
	public void MEM_stage(MEM_stage m, EX_stage x, int[]Main_Mem) {
		
		m.setInstruction(x.getRInstruction());
		
		//passing down for toString
		
		m.setWCalcBTA(x.getRIncrPC());
		
		m.setWSwValue(x.getRReadData2());
		
		m.setWALUResult(x.getRALUResult());
		
		m.setWriteRegNum(x.getRWriteRegNum());
		
		
		m.setOpc(x.getR_Opc());
		
		m.setNFunc(x.getR_NFunc());
		
		
		m.setRegDst(x.getRRegDst());
		
		m.setALUSrc(x.getRALUSrc());
		
		m.setALUOp(x.getRALUOp());
		
		m.setMemRead(x.getRMemRead());
		
		m.setMemWrite(x.getRMemWrite()); 
		
		m.setBranch(x.getRBranch()); 
		
		m.setMemtoReg(x.getRMemtoReg()); 

		m.setRegWrite(x.getRRegWrite());
		
			
		if (m.getWMemRead() == 1) { 
			
			m.setLWDataValue(Main_Mem[m.getWALUResult()]);
		}
		
		else if (m.getWMemWrite() == 1) {
																				
			Main_Mem[m.getWALUResult()] = m.getWSwValue();
		}
	}
	
	public void WB_stage(WB_stage b, MEM_stage m, int[] Regs) {
		
		b.setInstruction(m.getRInstruction());
		
		b.setMemtoReg(m.getRMemtoReg());
		  
		b.setRegWrite(m.getRRegWrite());
		
		b.setOpc(m.getR_Opc());
		
		b.setNFunc(m.getR_NFunc());
		
		b.setLWDataValue(m.getRLWDataValue());
		
		b.setWALUResult(m.getRALUResult());
		
		b.setWriteRegNum(m.getRWriteRegNum());
		
		if(b.getWRegWrite() == 1) {
			
			if (b.getWMemtoReg() == 1) { 
				
				Regs[b.getWWriteRegNum()] = b.getWLWDataValue();
				
			}
			
			else {
				
				Regs[b.getWWriteRegNum()] = b.getWLWDataValue();
			}
		}
	  }
	  
	public void Copy_write_to_read(IF_stage f, ID_stage d, EX_stage x, MEM_stage m, WB_stage b) {
		
		f.Copy_write_to_read();
		
		d.Copy_write_to_read();
		
		x.Copy_write_to_read();
		
		m.Copy_write_to_read();
		
		b.Copy_write_to_read();
		
	}
	
	public void Print_out_everything(ID_stage d, EX_stage x, MEM_stage m, WB_stage b , int[] Regs) {
		
		System.out.print(d.toString());
		
		System.out.print(x.toString());
		
		System.out.print(m.toString());
		
		System.out.print(b.toString());
		
		System.out.println("Registers: ");
		
		System.out.println("----------");
		
		for (int i = 0; i < Regs.length; i++){
			
			System.out.printf("%s%d%s%X\n","$", i , ": 0x",Regs[i]);
			
		}
		
		System.out.println("----------");
		
	}
	
	
	public static void main(String[] args) {
		
		PipelinedDatapathSimulation s = new PipelinedDatapathSimulation();
		
		s.proceed();
		
	}
}
