package main;

import boardelements.*;
import color.Color;
import update.Timer;


/**
 * Letrehozza a Default palyat
 *
 */
public class BoardCreater {
	public static EntryPoint createBoard(Timer t){
		EntryPoint ep = new EntryPoint(50, 300);
		
		Rail r1 = new Rail(40);
		Rail r2 = new Rail(40);
		Rail r3 = new Rail(40);
		Rail r4 = new Rail(40);
		Rail r5 = new Rail(40);
		Rail r6 = new Rail(40);
		Rail r7 = new Rail(40);
		Rail r8 = new Rail(40);
		Rail r9 = new Rail(40);
		Rail r10= new Rail(40);
		Rail r11 = new Rail(40);
		Rail r12 = new Rail(40);
		Rail r13 = new Rail(40);
		Rail r14 = new Rail(40);
		Rail r15 = new Rail(40);
		Rail r16 = new Rail(40);
		Rail r17 = new Rail(40);
		Rail r18 = new Rail(40);
		Rail r19 = new Rail(40);
		Rail r20 = new Rail(40);
		Rail r21 = new Rail(40);
		Rail r22 = new Rail(40);
		Rail r23 = new Rail(40);
		Rail r24 = new Rail(40);
		Rail r25 = new Rail(40);
		Rail r26 = new Rail(40);
		Rail r27 = new Rail(40);
		Rail r28 = new Rail(40);
		Rail r29 = new Rail(40);
		Rail r30 = new Rail(40);
		Rail r31 = new Rail(40);
		Rail r32 = new Rail(40);
		Rail r33 = new Rail(40);
		Rail r34 = new Rail(40);
		Rail r35 = new Rail(40);
		Rail r36 = new Rail(40);
		Rail r37 = new Rail(40);
		Rail r38 = new Rail(40);
		Rail r39 = new Rail(40);
		Rail r40 = new Rail(40);
		Rail r41 = new Rail(40);
		Rail r42 = new Rail(40);
		Rail r43 = new Rail(40);
		Rail r44 = new Rail(40);
		Rail r45 = new Rail(40);
		Rail r46 = new Rail(40);
		Rail r47 = new Rail(40);
		Rail r48 = new Rail(40);
		Rail r49 = new Rail(40);
		Rail r50 = new Rail(40);
		Rail r51 = new Rail(40);
		Rail r52 = new Rail(40);
		Rail r53 = new Rail(40);
		Rail r54 = new Rail(40);
		Rail r55 = new Rail(40);
		Rail r56 = new Rail(40);
		Rail r57 = new Rail(40);
		Rail r58 = new Rail(40);
		Rail r59 = new Rail(40);
		Rail r60 = new Rail(40);
		Rail r61 = new Rail(40);
		Rail r62 = new Rail(40);
		Rail r63 = new Rail(40);
		Rail r64 = new Rail(40);
		Rail r65 = new Rail(40);
		Rail r66 = new Rail(40);
		Rail r67 = new Rail(40);
		Rail r68 = new Rail(40);
		Rail r69 = new Rail(40);
		Rail r70 = new Rail(40);
		Rail r71 = new Rail(40);
		Rail r72 = new Rail(40);
		Rail r73 = new Rail(40);
		Rail r74 = new Rail(40);
		Rail r75 = new Rail(40);
		Rail r76 = new Rail(40);
		Rail r77 = new Rail(40);
		
		Switch sw1 = new Switch(0);
		Switch sw2 = new Switch(0);
		Switch sw3 = new Switch(0);
		Switch sw4 = new Switch(0);
		Switch sw5 = new Switch(0);
		
		TunnelOpportunity to1 = new TunnelOpportunity(0);
		TunnelOpportunity to2 = new TunnelOpportunity(0);
		TunnelOpportunity to3 = new TunnelOpportunity(0);
		TunnelOpportunity to4 = new TunnelOpportunity(0);

		Tunnel tun = new Tunnel(50);
		
		Station st1 = new Station(1, 40);
		Station st2 = new Station(1, 40);
		Station st3 = new Station(1, 40);

		ep.setEnds(null, r1);
		r1.setEnds(ep, r2);
		r2.setEnds(r1, sw1);
		sw1.setEnds(r2, r3);
		r3.setEnds(sw1, r4);
		r4.setEnds(r3, r5);
		r5.setEnds(r4, r6);
		r6.setEnds(r5, r7);
		r7.setEnds(r8, r6);
		r8.setEnds(r7, r9);
		r9.setEnds(r8, sw2);
		sw2.setEnds(r9, r11);
		r11.setEnds(sw2, r12);
		r12.setEnds(r11, sw3);
		sw3.setEnds(r12, r13);
		r13.setEnds(sw3, r14);	
		r14.setEnds(r13, r15);		
		r15.setEnds(r14, r16);		
		r16.setEnds(r15, r17);		
		r17.setEnds(r16, r18);		
		r18.setEnds(r17, r19);		
		r19.setEnds(r18, r20);		
		r20.setEnds(r19, st1);
		st1.setEnds(r20, r21);
		r21.setEnds(st1, r22);		
		r22.setEnds(r21, r23);		
		r23.setEnds(r22, r24);		
		r24.setEnds(sw3, sw3);		
		sw3.addExit(r24);
		sw2.addExit(r10);
		//sw2.changeDir();
		r10.setEnds(sw2, to1);
		to1.setEnds(r10, tun);
		to1.unlock();
		
		sw1.addExit(r25);
		sw1.changeDir();
		r25.setEnds(sw1, r26);		
		r26.setEnds(r25, r27);		
		r27.setEnds(r26, r28);		
		r28.setEnds(r27, r29);
		r29.setEnds(r28, sw4);
		sw4.setEnds(r29, r43);
		r43.setEnds(sw4, r44);
		r44.setEnds(r43, r45);
		r45.setEnds(r44, r46);
		r46.setEnds(r45, r47);
		r47.setEnds(r46, to3);
		to3.setEnds(r47, tun);
		to3.unlock();
		
		sw4.addExit(r30);
		r30.setEnds(sw4, r31);
		r31.setEnds(r30, r32);
		r32.setEnds(r31, r33);
		r33.setEnds(r32, r34);
		r34.setEnds(r33, r35);
		r35.setEnds(r34, r36);
		r36.setEnds(r35, r37);
		r37.setEnds(r36, r38);
		r38.setEnds(r37, r39);
		r39.setEnds(r38, r40);
		r40.setEnds(r39, st2);
		st2.setEnds(r40, r41);
		r41.setEnds(st2, r42);
		r42.setEnds(r41, to2);
		to2.setEnds(r42, tun);
		
		to4.setEnds(tun, sw5);
		sw5.setEnds(to4, r48);
		r48.setEnds(sw5, r49);
		r49.setEnds(r48, r50);
		r50.setEnds(r49, r51);
		r51.setEnds(r50, r52);
		r52.setEnds(r51, r53);
		r53.setEnds(r52, r54);
		r54.setEnds(r53, r55);
		r55.setEnds(r54, r56);
		r56.setEnds(r55, r57);
		r57.setEnds(r56, r58);
		r58.setEnds(r57, r59);
		r59.setEnds(r58, r60);
		r60.setEnds(r59, r61);
		r61.setEnds(r60, r62);
		r62.setEnds(r61, r63);
		r63.setEnds(r62, r64);
		r64.setEnds(r63, r65);
		r65.setEnds(r64, st3);
		st3.setEnds(r65, r66);
		r66.setEnds(st3, r67);
		r67.setEnds(r66, r68);
		r68.setEnds(r67, r69);
		r69.setEnds(r68, r70);
		r70.setEnds(r69, r71);
		r71.setEnds(r70, r72);
		r72.setEnds(r71, r73);
		r73.setEnds(r72, r74);
		r74.setEnds(r73, r75);
		r75.setEnds(r74, r76);
		r76.setEnds(r75, r77);
		r77.setEnds(r76, sw5);
		sw5.addExit(r77);
		
		tun.setEnds(to1, to3);
		
		return ep;
	}
}
