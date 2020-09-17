package com.javalec.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.javalec.function.Member;
import com.javalec.function.rank;
import com.javalec.setup.SH_Bean;
import com.javalec.setup.character_level;
import com.javalec.setup.db_Share_Var;
import javax.swing.border.LineBorder;

public class mainPanel extends JPanel {
	
	rank rank = new rank();
	
	public static String logId;
	public JLabel lbImage;
	private JLabel lbId;
	private JLabel lbName;
	private JLabel lbLevel;
	private JLabel lbMaxSco;
	private JSeparator sprt1;
	private JLabel lblNewLabel;
	private JSeparator sprt2;
	public JTextField tfId;
	public JTextField tfName;
	public JTextField tfLevel;
	public JTextField tfMaxSco;
	int windAct = 0;
	JLabel lbExpNum1;
	JLabel lbExpNum2;
	JLabel lbExpNum3;
	private JLabel lbIdExp1;
	private JLabel lbIdExp2;
	private JLabel lbIdExp3;
	private JLabel lbIdExp4;
	private JLabel lbIdExp5;
	private JLabel lbExp1;
	private JLabel lbExp2;
	private JLabel lbExp3;
	private JLabel lbExp4;
	private JLabel lbExp5;
	private JSeparator separator;
	private JLabel lbIdMaxSco1;
	private JLabel lbIdMaxSco2;
	private JLabel lbIdMaxSco3;
	private JLabel lbIdMaxSco4;
	private JLabel lbIdMaxSco5;
	JLabel lbMaxNum1;
	JLabel lbMaxNum2;
	JLabel lbMaxNum3;
	private JLabel lbMaxsco1;
	private JLabel lbMaxsco2;
	private JLabel lbMaxsco3;
	private JLabel lbMaxsco4;
	private JLabel lbMaxsco5;

	/**
	 * Create the panel.
	 */
	public mainPanel() {
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		setBackground(new Color(255, 244, 213));
		setLayout(null);
		add(getLbImage());
		add(getLbId());
		add(getLbName());
		add(getLbLevel());
		add(getLbMaxSco());
		add(getSprt1());
		add(getLblNewLabel());
		add(getSprt2());
		add(getTfId());
		add(getTfName());
		add(getTfLevel());
		add(getTfMaxSco());
		add(getLbExpNum1());
		add(getLbExpNum2());
		add(getLbExpNum3());
		add(getLbIdExp1());
		add(getLbIdExp2());
		add(getLbIdExp3());
		add(getLbIdExp4());
		add(getLbIdExp5());
		add(getLbExp1());
		add(getLbExp2());
		add(getLbExp3());
		add(getLbExp4());
		add(getLbExp5());
		add(getSeparator());
		add(getLbIdMaxSco1());
		add(getLbIdMaxSco2());
		add(getLbIdMaxSco3());
		add(getLbIdMaxSco4());
		add(getLbIdMaxSco5());
		add(getLbMaxNum1());
		add(getLbMaxNum2());
		add(getLbMaxNum3());
		add(getLbMaxsco1());
		add(getLbMaxsco2());
		add(getLbMaxsco3());
		add(getLbMaxsco4());
		add(getLbMaxsco5());
		setBounds(100, 100, 900, 700);
	}
	
	private JLabel getLbImage() {
		if (lbImage == null) {
			lbImage = new JLabel("Image");
			lbImage.setHorizontalAlignment(SwingConstants.CENTER);
			lbImage.setBounds(196, 17, 160, 160);
		}
		return lbImage;
	}
	private JLabel getLbId() {
		if (lbId == null) {
			lbId = new JLabel("아이디");
			lbId.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			lbId.setBounds(405, 29, 80, 16);
		}
		return lbId;
	}
	private JLabel getLbName() {
		if (lbName == null) {
			lbName = new JLabel("이름");
			lbName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			lbName.setBounds(405, 69, 80, 16);
		}
		return lbName;
	}
	private JLabel getLbLevel() {
		if (lbLevel == null) {
			lbLevel = new JLabel("레벨");
			lbLevel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			lbLevel.setBounds(405, 109, 80, 16);
		}
		return lbLevel;
	}
	private JLabel getLbMaxSco() {
		if (lbMaxSco == null) {
			lbMaxSco = new JLabel("최고 점수");
			lbMaxSco.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			lbMaxSco.setBounds(405, 149, 80, 16);
		}
		return lbMaxSco;
	}
	private JSeparator getSprt1() {
		if (sprt1 == null) {
			sprt1 = new JSeparator();
			sprt1.setForeground(Color.BLACK);
			sprt1.setBounds(33, 203, 833, 12);
		}
		return sprt1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("키보드타이쿤 랭킹");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
			lblNewLabel.setBounds(343, 218, 214, 30);
		}
		return lblNewLabel;
	}
	private JSeparator getSprt2() {
		if (sprt2 == null) {
			sprt2 = new JSeparator();
			sprt2.setForeground(Color.BLACK);
			sprt2.setBounds(33, 503, 833, 12);
		}
		return sprt2;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setEditable(false);
			tfId.setBorder(new LineBorder(Color.GRAY));
			tfId.setBackground(new Color(255,255,255));
			tfId.setBounds(498, 24, 130, 26);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEditable(false);
			tfName.setBackground(new Color(255,255,255));
			tfName.setBorder(new LineBorder(Color.GRAY));
			tfName.setColumns(10);
			tfName.setBounds(497, 64, 130, 26);
		}
		return tfName;
	}
	private JTextField getTfLevel() {
		if (tfLevel == null) {
			tfLevel = new JTextField();
			tfLevel.setEditable(false);
			tfLevel.setBackground(new Color(255,255,255));
			tfLevel.setBorder(new LineBorder(Color.GRAY));
			tfLevel.setColumns(10);
			tfLevel.setBounds(498, 104, 130, 26);
		}
		return tfLevel;
	}
	private JTextField getTfMaxSco() {
		if (tfMaxSco == null) {
			tfMaxSco = new JTextField();
			tfMaxSco.setEditable(false);
			tfMaxSco.setBackground(new Color(255,255,255));
			tfMaxSco.setBorder(new LineBorder(Color.GRAY));
			tfMaxSco.setColumns(10);
			tfMaxSco.setBounds(498, 144, 130, 26);
		}
		return tfMaxSco;
	}
	
	private JLabel getLbExpNum1() {
		if (lbExpNum1 == null) {
			lbExpNum1 = new JLabel("1");
			lbExpNum1.setIcon(new ImageIcon("/Users/tj/Documents/SEUNGHEE/managementTycoon/imageIcon/CrownGoldd.png"));
			lbExpNum1.setBounds(100, 260, 30, 30);
		}
		return lbExpNum1;
	}
	private JLabel getLbExpNum2() {
		if (lbExpNum2 == null) {
			lbExpNum2 = new JLabel("2");
			lbExpNum2.setIcon(new ImageIcon("/Users/tj/Documents/SEUNGHEE/managementTycoon/imageIcon/CrownSilver.png"));
			lbExpNum2.setBounds(100, 310, 30, 30);
		}
		return lbExpNum2;
	}
	private JLabel getLbExpNum3() {
		if (lbExpNum3 == null) {
			lbExpNum3 = new JLabel("3");
			lbExpNum3.setIcon(new ImageIcon("/Users/tj/Documents/SEUNGHEE/managementTycoon/imageIcon/CrownCooper.png"));
			lbExpNum3.setBounds(100, 360, 30, 30);
		}
		return lbExpNum3;
	}
	private JLabel getLbIdExp1() {
		if (lbIdExp1 == null) {
			lbIdExp1 = new JLabel("");
			lbIdExp1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbIdExp1.setBounds(200, 267, 61, 16);
		}
		return lbIdExp1;
	}
	private JLabel getLbIdExp2() {
		if (lbIdExp2 == null) {
			lbIdExp2 = new JLabel("");
			lbIdExp2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbIdExp2.setBounds(200, 317, 61, 16);
		}
		return lbIdExp2;
	}
	private JLabel getLbIdExp3() {
		if (lbIdExp3 == null) {
			lbIdExp3 = new JLabel("");
			lbIdExp3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbIdExp3.setBounds(200, 367, 61, 16);
		}
		return lbIdExp3;
	}
	private JLabel getLbIdExp4() {
		if (lbIdExp4 == null) {
			lbIdExp4 = new JLabel("");
			lbIdExp4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbIdExp4.setBounds(200, 417, 61, 16);
		}
		return lbIdExp4;
	}
	private JLabel getLbIdExp5() {
		if (lbIdExp5 == null) {
			lbIdExp5 = new JLabel("");
			lbIdExp5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbIdExp5.setBounds(200, 457, 61, 16);
		}
		return lbIdExp5;
	}
	private JLabel getLbExp1() {
		if (lbExp1 == null) {
			lbExp1 = new JLabel("");
			lbExp1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbExp1.setBounds(300, 267, 61, 16);
		}
		return lbExp1;
	}
	private JLabel getLbExp2() {
		if (lbExp2 == null) {
			lbExp2 = new JLabel("");
			lbExp2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbExp2.setBounds(300, 317, 61, 16);
		}
		return lbExp2;
	}
	private JLabel getLbExp3() {
		if (lbExp3 == null) {
			lbExp3 = new JLabel("");
			lbExp3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbExp3.setBounds(300, 367, 61, 16);
		}
		return lbExp3;
	}
	private JLabel getLbExp4() {
		if (lbExp4 == null) {
			lbExp4 = new JLabel("");
			lbExp4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbExp4.setBounds(300, 417, 61, 16);
		}
		return lbExp4;
	}
	private JLabel getLbExp5() {
		if (lbExp5 == null) {
			lbExp5 = new JLabel("");
			lbExp5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbExp5.setBounds(300, 457, 61, 16);
		}
		return lbExp5;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setBounds(444, 256, 12, 235);
		}
		return separator;
	}
	private JLabel getLbIdMaxSco1() {
		if (lbIdMaxSco1 == null) {
			lbIdMaxSco1 = new JLabel("");
			lbIdMaxSco1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbIdMaxSco1.setBounds(610, 267, 90, 16);
		}
		return lbIdMaxSco1;
	}
	private JLabel getLbIdMaxSco2() {
		if (lbIdMaxSco2 == null) {
			lbIdMaxSco2 = new JLabel("");
			lbIdMaxSco2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbIdMaxSco2.setBounds(610, 317, 90, 16);
		}
		return lbIdMaxSco2;
	}
	private JLabel getLbIdMaxSco3() {
		if (lbIdMaxSco3 == null) {
			lbIdMaxSco3 = new JLabel("");
			lbIdMaxSco3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbIdMaxSco3.setBounds(610, 367, 90, 16);
		}
		return lbIdMaxSco3;
	}
	private JLabel getLbIdMaxSco4() {
		if (lbIdMaxSco4 == null) {
			lbIdMaxSco4 = new JLabel("");
			lbIdMaxSco4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbIdMaxSco4.setBounds(610, 417, 90, 16);
		}
		return lbIdMaxSco4;
	}
	private JLabel getLbIdMaxSco5() {
		if (lbIdMaxSco5 == null) {
			lbIdMaxSco5 = new JLabel("");
			lbIdMaxSco5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbIdMaxSco5.setBounds(610, 457, 90, 16);
		}
		return lbIdMaxSco5;
	}
	private JLabel getLbMaxNum1() {
		if (lbMaxNum1 == null) {
			lbMaxNum1 = new JLabel("1");
			lbMaxNum1.setIcon(new ImageIcon("/Users/tj/Documents/SEUNGHEE/managementTycoon/imageIcon/CrownGoldd.png"));
			lbMaxNum1.setBounds(510, 260, 30, 30);
		}
		return lbMaxNum1;
	}
	private JLabel getLbMaxNum2() {
		if (lbMaxNum2 == null) {
			lbMaxNum2 = new JLabel("2");
			lbMaxNum2.setIcon(new ImageIcon("/Users/tj/Documents/SEUNGHEE/managementTycoon/imageIcon/CrownSilver.png"));
			lbMaxNum2.setBounds(510, 310, 30, 30);
		}
		return lbMaxNum2;
	}
	private JLabel getLbMaxNum3() {
		if (lbMaxNum3 == null) {
			lbMaxNum3 = new JLabel("3");
			lbMaxNum3.setIcon(new ImageIcon("/Users/tj/Documents/SEUNGHEE/managementTycoon/imageIcon/CrownCooper.png"));
			lbMaxNum3.setBounds(510, 360, 30, 30);
		}
		return lbMaxNum3;
	}
	private JLabel getLbMaxsco1() {
		if (lbMaxsco1 == null) {
			lbMaxsco1 = new JLabel("");
			lbMaxsco1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbMaxsco1.setBounds(710, 267, 90, 16);
		}
		return lbMaxsco1;
	}
	private JLabel getLbMaxsco2() {
		if (lbMaxsco2 == null) {
			lbMaxsco2 = new JLabel("");
			lbMaxsco2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbMaxsco2.setBounds(710, 317, 90, 16);
		}
		return lbMaxsco2;
	}
	private JLabel getLbMaxsco3() {
		if (lbMaxsco3 == null) {
			lbMaxsco3 = new JLabel("");
			lbMaxsco3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbMaxsco3.setBounds(710, 367, 90, 16);
		}
		return lbMaxsco3;
	}
	private JLabel getLbMaxsco4() {
		if (lbMaxsco4 == null) {
			lbMaxsco4 = new JLabel("");
			lbMaxsco4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbMaxsco4.setBounds(710, 417, 90, 16);
		}
		return lbMaxsco4;
	}
	private JLabel getLbMaxsco5() {
		if (lbMaxsco5 == null) {
			lbMaxsco5 = new JLabel("");
			lbMaxsco5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbMaxsco5.setBounds(710, 457, 90, 16);
		}
		return lbMaxsco5;
	}
	
	// Image Null exception 처리
	void getMaininfo(String loginId) {
		Member member = new Member(loginId);
		ArrayList<SH_Bean> bean = new ArrayList<SH_Bean>();
		//setMemberInfo
		bean = member.memeber_info();
		tfName.setText(bean.get(0).getName());
		tfId.setText(bean.get(0).getId());
		
		character_level chLevel = new character_level(bean.get(0).getExp());
		int level = chLevel.character_level();
		
		tfLevel.setText(Integer.toString(level));
		tfMaxSco.setText(Integer.toString(bean.get(0).getMaxScore()));
		//setMemberProfile
		String filePath = Integer.toString(db_Share_Var.filename);		
		
		lbImage.setIcon(new ImageIcon(filePath));
		lbImage.setText("");
		lbImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		int imageWidth;
		int imageHeight;
		double ratio;
		
		Image image = null;
		
		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (image == null) {
			lbImage.setText("사진 없음");
		} else {
			//Image Resize 
			imageWidth = image.getWidth(null);
			imageHeight = image.getHeight(null);
			int w = 0;
			int h = 0;
		
			if (imageWidth < imageHeight) {
				ratio = 160/(double)imageHeight;
				w = (int)(imageWidth*ratio);
				h = (int)(imageHeight*ratio);
			} else {
				ratio = 160/(double)imageWidth;
				w = (int)(imageWidth*ratio);
				h = (int)(imageHeight*ratio);
		}
		
		try {
			Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_FAST);
			ImageIcon ic = new ImageIcon(resizeImage);
			lbImage.setIcon(ic);	
		} catch (Exception e1) {
			e1.printStackTrace();
			}
		}
	}
	

	void getRanking() {
		ArrayList<SH_Bean> rankBean = new ArrayList<SH_Bean>();
		Member mb = new Member();
		
		rankBean = mb.getExpRank();
		
		lbIdExp1.setText(rankBean.get(0).getId());
		lbIdExp2.setText(rankBean.get(1).getId());
		lbIdExp3.setText(rankBean.get(2).getId());
		lbIdExp4.setText(rankBean.get(3).getId());
		lbIdExp5.setText(rankBean.get(4).getId());
		
		lbExp1.setText(Integer.toString(rankBean.get(0).getExp()));
		lbExp2.setText(Integer.toString(rankBean.get(1).getExp()));
		lbExp3.setText(Integer.toString(rankBean.get(2).getExp()));
		lbExp4.setText(Integer.toString(rankBean.get(3).getExp()));
		lbExp5.setText(Integer.toString(rankBean.get(4).getExp()));		
		
		rankBean = mb.getMaxRank();
		
		lbIdMaxSco1.setText(rankBean.get(0).getId());
		lbIdMaxSco2.setText(rankBean.get(1).getId());
		lbIdMaxSco3.setText(rankBean.get(2).getId());
		lbIdMaxSco4.setText(rankBean.get(3).getId());
		lbIdMaxSco5.setText(rankBean.get(4).getId());
		
		lbMaxsco1.setText(Integer.toString(rankBean.get(0).getMaxScore()));
		lbMaxsco2.setText(Integer.toString(rankBean.get(1).getMaxScore()));
		lbMaxsco3.setText(Integer.toString(rankBean.get(2).getMaxScore()));
		lbMaxsco4.setText(Integer.toString(rankBean.get(3).getMaxScore()));
		lbMaxsco5.setText(Integer.toString(rankBean.get(4).getMaxScore()));		
	}	
}
