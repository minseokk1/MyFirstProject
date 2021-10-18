
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class MyDialog extends JDialog {
	static List<Paper> Paperlist;
	static JLabel[] lbllist = new JLabel[5];

	public static List<Paper> getPaperlist() {
		return Paperlist;
	}

	public JLabel[] getLbllist() {
		return lbllist;
	}

	public MyDialog(JPanel numpnl, int n) { // (타입, select 된 수 n)
		Paperlist = new ArrayList<>();
		setModal(true);

		ImageIcon icon = new ImageIcon("lotto3.png");
		JLabel img = new JLabel(icon);

		// 초기화후에 남아있는 레이블 싹 다 지우기
		numpnl.removeAll();
		
		for (int i = 0; i < 5; i++) {
			lbllist[i] = new JLabel();
			lbllist[i].setFont(new Font(lbllist[i].getFont().getName(), Font.BOLD, 16));
			numpnl.add(lbllist[i]);
		}

		JPanel Paperpnl = new JPanel();

		// 복권지(new Paper)(class) 몇 장 나올것인가
		for (int i = 0; i < n; i++) {
			Paper page = new Paper();
			Paperpnl.add(page); // n의 갯수만큼 pnlCount에 class Paper를 추가한다.
			Paperlist.add((Paper) page);
		}

		// ok버튼
		JButton btnok = new JButton("OK");
		btnok.addActionListener(new ActionListener() {
			String alphabet;
			JFrame frame = new JFrame();

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean chkEmpty = true;

				

				// 다이얼로그 ok 누르면 첫장 "확인",콤보박스 비활성화
				Start.btnConfirm.setEnabled(false);
				Start.combo.setEnabled(false);
//					Start.reset.setEnabled(true);
//					Start.Del.setEnabled(true);
//					Start.result.setEnabled(true);		

				for (int j = 0; j < Paperlist.size(); j++) {
					if (Paperlist.get(j).getLotto() == null || Paperlist.get(j).getLotto().length < 6) {
						chkEmpty = false;
						break;
					}
				}
				if (!chkEmpty) {
					JOptionPane.showMessageDialog(frame, "선택 후 눌려주세요", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					for (int i = 0; i < Paperlist.size(); i++) {
						dispose(); // 닫기

						// 결제금액 출력하기
						int price = 0;
						for (int j = 0; j < n; j++) {
							price += 1000;
						}
						Start.price2.setText("결제금액 : " + price + "원");
						Start.reset.setEnabled(true);
						Start.Del.setEnabled(true);
						Start.result.setEnabled(true);

						if (i == 0) {
							alphabet = "A";
						} else if (i == 1) {
							alphabet = "B";
						} else if (i == 2) {
							alphabet = "C";
						} else if (i == 3) {
							alphabet = "D";
						} else if (i == 4) {
							alphabet = "E";
						}

						String temp = Arrays.toString(Paperlist.get(i).getLotto());
						if (Paperlist.get(i).getRadio1().isSelected()) {
							lbllist[i].setText(alphabet + "    " + "자동" + "    " + temp);
						} else {
							lbllist[i].setText(alphabet + "    " + "수동" + "    " + temp);
						}
					}
				}
				// Start창을 다시 pack해줌
				Lotto.start.pack();
			}
		});
		add(img, "North");
		add(Paperpnl);
		add(btnok, "South");
		pack();

	}

}