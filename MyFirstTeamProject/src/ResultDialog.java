
import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ResultDialog extends JDialog {
	private int[] winlotto;

	public ResultDialog() {
		setModal(true);

		JPanel pnl = new JPanel();

		// 이미지
		JPanel pnlPhoto = new JPanel();
		ImageIcon icon = new ImageIcon("lotto3.png");
		JLabel lblPhoto = new JLabel(icon);
		pnlPhoto.add(lblPhoto);

//		// 당첨번호
		JPanel plnwin = new JPanel();
		JLabel lblwin = new JLabel();
		JLabel lblwin2 = new JLabel();
		winlotto = new int[7];
		// 랜덤 숫자 6개 뽑기
		for (int i = 0; i < winlotto.length; i++) {
			winlotto[i] = (int) (Math.random() * 45 + 1);
			// 중복 제거
			for (int j = 0; j < i; j++) {
				if (winlotto[i] == winlotto[j]) {
					i--;
				}
			}
		}

		// 7개 당첨번호중에 보너스번호는 순서에 제외하고 나머지 6개만 버블정렬
		// 보너스번호는 순서에 상관하고 싶지않아서
		// winlotto[0~5]를 winlotto2[]가져옴
		int[] winlotto2 = new int[6];
		for (int i = 0; i < winlotto2.length; i++) {
			winlotto2[i] = winlotto[i];
		}
		// winlotto2순서 배열
		int temp;
		for (int i = 0; i < winlotto2.length; i++) {
			for (int j = 0; j < i; j++) {
				if (winlotto2[i] < winlotto2[j]) {
					temp = winlotto2[i];
					winlotto2[i] = winlotto2[j];
					winlotto2[j] = temp;
				}
			}
		}

		lblwin.setText(winlotto2[0] + "   " + winlotto2[1] + "   " + winlotto2[2] + "   " + winlotto2[3] + "   "
				+ winlotto2[4] + "   " + winlotto2[5] + "   +   ");
		lblwin2.setText(" " + winlotto[6] + " ");
		lblwin2.setOpaque(true);
		lblwin2.setBackground(Color.yellow);
//		lblwin.setPreferredSize(new Dimension(250, 25));
		plnwin.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.RED, 1), "당첨 번호"));
		plnwin.add(lblwin);
		plnwin.add(lblwin2);

		// 사용자 번호
		JPanel hap = new JPanel();
//		hap.setPreferredSize(new Dimension(360, 250));
		JPanel[] pnlnum = new JPanel[5];
		JLabel[][] lblnum2 = new JLabel[5][6];
		JLabel[] lblnum1 = new JLabel[5];
		JLabel[] lblnum3 = new JLabel[5];
		String alphabet = "A";
		hap.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.RED, 1), "선택한 번호"));
//		hap.setLayout(new BoxLayout(hap, BoxLayout.Y_AXIS));
		for (int i = 0; i < MyDialog.getPaperlist().size(); i++) {
			pnlnum[i] = new JPanel();
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
			lblnum1[i] = new JLabel();
			if (MyDialog.Paperlist.get(i).getRadio1().isSelected()) {
				lblnum1[i].setText(alphabet + "   " + "자동" + "   ");
			} else {
				lblnum1[i].setText(alphabet + "   " + "수동" + "   ");
			}
			pnlnum[i].add(lblnum1[i]);

			int count1 = 0;
			int count2 = 0;
			for (int j = 0; j < lblnum2[i].length; j++) {
				int num = MyDialog.getPaperlist().get(i).getLotto()[j]; // int[] swsss = { 1, 2 }; int n = swsss[1];
				lblnum2[i][j] = new JLabel();
				lblnum2[i][j].setText(String.valueOf(num));
				lblnum2[i][j].setPreferredSize(new Dimension(20, 20));
				pnlnum[i].add(lblnum2[i][j]);
				if (lblnum2[i][j].getText().equals(String.valueOf(winlotto2[0]))) {
					lblnum2[i][j].setOpaque(true);
					lblnum2[i][j].setForeground(Color.red);
					count1++;
				}
				if (lblnum2[i][j].getText().equals(String.valueOf(winlotto2[1]))) {
					lblnum2[i][j].setOpaque(true);
					lblnum2[i][j].setForeground(Color.blue);
					count1++;
				}
				if (lblnum2[i][j].getText().equals(String.valueOf(winlotto2[2]))) {
					lblnum2[i][j].setOpaque(true);
					lblnum2[i][j].setForeground(Color.green);
					count1++;
				}
				if (lblnum2[i][j].getText().equals(String.valueOf(winlotto2[3]))) {
					lblnum2[i][j].setOpaque(true);
					lblnum2[i][j].setForeground(Color.orange);
					count1++;
				}
				if (lblnum2[i][j].getText().equals(String.valueOf(winlotto2[4]))) {
					lblnum2[i][j].setOpaque(true);
					lblnum2[i][j].setForeground(Color.magenta);
					count1++;
				}
				if (lblnum2[i][j].getText().equals(String.valueOf(winlotto2[5]))) {
					lblnum2[i][j].setOpaque(true);
					lblnum2[i][j].setForeground(Color.pink);
					count1++;
				}
			}
			for (int j = 0; j < lblnum2[i].length; j++) {
				
				if (lblnum2[i][j].getText().equals(String.valueOf(winlotto[6]))) {
					if (count1 == 5) {
						lblnum2[i][j].setOpaque(true);
						lblnum2[i][j].setForeground(Color.yellow);
						count2++;
					}
				}
			}
			lblnum3[i] = new JLabel();
			if (count1 == 6) {
				lblnum3[i].setText("1등");
			} else if (count1 == 5 && count2 == 1) {
				lblnum3[i].setText("2등");
			} else if (count1 == 5) {
				lblnum3[i].setText("3등");
			} else if (count1 == 4) {
				lblnum3[i].setText("4등");
			} else if (count1 == 3) {
				lblnum3[i].setText("5등");
			} else {
				lblnum3[i].setText("나락");
			}
			pnlnum[i].add(lblnum3[i]);

			hap.add(pnlnum[i]);
//		        label.setBackground(Color.RED);

		}

		pnl.add(pnlPhoto, "North");
		pnl.add(plnwin, "Center");
		pnl.add(hap);
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		hap.setLayout(new BoxLayout(hap, BoxLayout.Y_AXIS));
		add(pnl);
		pack();
//		setSize(400, 500);
	}
}
