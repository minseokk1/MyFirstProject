
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

class Start extends JFrame { // 첫번째 창
	static JLabel price2 = new JLabel("인생은 한방 로또게임!!");
	static JButton reset;
	static JButton Del;
	static JButton result;
	JDialog dialog;
	static JButton btnConfirm;
	static JComboBox<String> combo;
	
	public Start() {
		
		// 이미지
		JPanel pnlSum = new JPanel();
		pnlSum.setLayout(new BoxLayout(pnlSum, BoxLayout.Y_AXIS)); // 가로 세로 정렬
		JPanel pnlImg = new JPanel();
		ImageIcon icon = new ImageIcon("lotto3.png");
		JLabel img = new JLabel(icon);

		// 콤보박스
		JPanel pnlChoice = new JPanel();
		String[] num = { "1", "2", "3", "4", "5" };
		combo = new JComboBox<String>(num);
		combo.setLocation(1, 1);

		pnlImg.add(img); // 이미지

		JLabel lblCount = new JLabel("적용수량");
		pnlChoice.add(lblCount); // "적용수량"
		pnlChoice.add(combo); // "콤보"

		// 상단부
		pnlSum.add(pnlImg);
		pnlSum.add(pnlChoice);

		// 사용자 선택창 테두리 - 중간부
		JPanel pnlCenter = new JPanel();
		JPanel numpnl = new JPanel();
		// numpnl박스정렬(안에 레이블쌓아야함),중앙정렬
		numpnl.setLayout(new BoxLayout(numpnl, BoxLayout.Y_AXIS));
		numpnl.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		// 가격("인생은 한방 로또게임!!")
		JPanel price = new JPanel();
		price.add(price2);

		// 나중에 레이아웃 고치기
		pnlCenter.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.RED, 1), "사용자 선택 결과창"));
		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
		pnlCenter.add(numpnl);
		pnlCenter.add(price);

		// 확인 버튼
		btnConfirm = new JButton("확인");
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String select = (String) combo.getSelectedItem(); // 콤보박스가 String으로 정의 되었기에 앞에 (String)
				dialog = new MyDialog(numpnl, Integer.valueOf(select)); // 선택된 값을 int값으로 가져온다
				dialog.setVisible(true);
			}
		});
		pnlChoice.add(btnConfirm); // "확인" 버튼

		// pnlChoice 오른쪽으로 배렬
		pnlChoice.setAlignmentX(LEFT_ALIGNMENT);

		// 하단 버튼 세 개 - 하단부
		JPanel pnlBottom = new JPanel();
		reset = new JButton("초기화");
		Del = new JButton("수정/삭제");
		result = new JButton("결과");

		pnlBottom.add(reset);
		pnlBottom.add(Del);
		pnlBottom.add(result);

		// 창 버튼 enabled 초기값
		reset.setEnabled(false);
		Del.setEnabled(false);
		result.setEnabled(false);
		// 초기화 버튼 액션
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// lbllist안 text 지우기
				for (int i = 0; i < MyDialog.lbllist.length; i++) {
					MyDialog.lbllist[i].setText(null);
				}
				// 결제금액 지우기
				price2.setText(null);

				// paper정보 리셋
				MyDialog.getPaperlist().clear();

//				List<JLabel> lbl = new ArrayList<>(Arrays.asList(MyDialog.lbllist)); //[] -> ArrayList
//				lbl.clear(); //ArrayList 내용삭제
//				MyDialog.lbllist = lbl.toArray(new JLabel[lbl.size()]); //ArrayList -> []
//				System.out.println(Arrays.toString(MyDialog.lbllist));
				// 콤보,확인버튼 살아나라
				combo.setEnabled(true);
				btnConfirm.setEnabled(true);
				// 초기화,수정,결과 비활성화
				reset.setEnabled(false);
				Del.setEnabled(false);
				result.setEnabled(false);

			}
		});

		// 수정/삭제 버튼
		Del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});

		// 결과버튼
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog resultdialog = new ResultDialog();
				resultdialog.setVisible(true);
				resultdialog.setResizable(false);// 사이즈조절금지
			}
		});

		add(pnlSum, "North");
		add(pnlCenter, "Center");
		add(pnlBottom, "South");

		showGUI();

	}

	private void showGUI() {
		pack();
//		setSize(350, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}

public class Lotto {
	static Start start;

	public static void main(String[] args) {
		start = new Start();
	}

}
