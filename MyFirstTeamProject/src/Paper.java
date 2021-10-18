
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class Paper extends JPanel { // 로또종이를 클래스로 따로 빼준 후 JPanel 상속. 없으면 add가 안됨
	private JRadioButton radio1;
	private Integer [] lotto;
	
	public Paper() {
		
		JPanel pnlhap = new JPanel(); // 수동,자동,리셋  +  1~45
		
		JPanel pnlbuttons = new JPanel(); // 수동,자동,리셋
		JPanel pnlsu = new JPanel(); // 1~45

		ButtonGroup group = new ButtonGroup();
		radio1 = new JRadioButton("자동");
		JRadioButton radio2 = new JRadioButton("수동");
		JButton delete = new JButton("삭제");

		add(pnlbuttons);
		group.add(radio1);
		group.add(radio2);

		pnlbuttons.add(radio1);
		pnlbuttons.add(radio2);
		pnlbuttons.add(delete);

		// 1~45 숫자버튼
		JButton[] num = new JButton[45];
		for (int i = 0; i < num.length; i++) {
			num[i] = new JButton(String.valueOf(i + 1)); // 버튼을 생성 후 String.valueOf(i + 1) 숫자 적힌 버튼 만든다.
															// JButton은 String만 받음
			num[i].setPreferredSize(new Dimension(50, 50));
			pnlsu.add(num[i]); // 숫자버튼 num을 pnlsu 라는 패널에 넣는다.
		}
		
		//수동 배열
		Set<Integer> sudong = new TreeSet<Integer>();
		for (int i = 0; i < num.length; i++) {
			num[i].addActionListener(new ActionListener() { // 숫자들에 액션리스너
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton) e.getSource();
					radio2.setSelected(true); // 숫자 버튼 선택될 때 수동라디오박스 체크됨

					// 수동 숫자 6개만 선택되게 하기
					int count = 0;
					for (int j = 0; j < num.length; j++) {
						if (!num[j].isEnabled()) { // 활성화 되지 않은것. 눌러진것
							count++;
						}
					}
					if (count >= 6) {
						btn.setEnabled(true); // 6개 이상이면 활성화. 눌러지지않는다.
					} else {
						btn.setEnabled(false); // 수동키 콘솔창에 띄우기
						String selsu = btn.getActionCommand();
						sudong.add(Integer.parseInt(selsu)); //set에서 배열로 바꿀때는 int ㄴㄴ 
						lotto = sudong.toArray(new Integer[0]); // 위에 배열정의할때 integer
					}
				}
			});
			num[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JButton btn = (JButton) e.getSource();
					if (!btn.isEnabled() && radio2.isSelected()) { //radio2.isSelected() : 자동시에 버튼안눌려짐
						btn.setEnabled(true);
						sudong.remove(Integer.parseInt(btn.getText())); // 원래있던 수동값 지움
						lotto = sudong.toArray(new Integer[0]);
					}
				}
				
			});
		}
		
		// 자동 누르면 버튼 num 모두 비활성화 
		radio1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					for (int i = 0; i < num.length; i++) {
						num[i].setEnabled(false);
					}
					
					lotto = new Integer[6];
					// 랜덤 숫자 6개 뽑기
					for (int i = 0; i < lotto.length; i++) {
						lotto[i] = (int) (Math.random() * 45 + 1);
						// 중복 제거
						for (int j = 0; j < i; j++) {
							if (lotto[i] == lotto[j]) {
								i--;
							}
						}
					}
					// 크기 비교해서 나열
					int temp;
					for (int i = 0; i < lotto.length; i++) {
						for (int j = 0; j < i; j++) {
							if (lotto[i] < lotto[j]) {
								temp = lotto[i];
								lotto[i] = lotto[j];
								lotto[j] = temp;
							}
						}
					}
//					System.out.println(Arrays.toString(lotto));
				} /* 안 눌러져있는 상태일때 하는 행동 */
				else if (e.getStateChange() == ItemEvent.DESELECTED) {
					for (int i = 0; i < num.length; i++) {
						num[i].setEnabled(true);
					}
				}
			}
		});
		
		// 삭제 누르면 누른거 모두 활성화
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				group.clearSelection();
				for (int i = 0; i < num.length; i++) {
					num[i].setEnabled(true);
				}
				//자동눌렀다가 삭제버튼눌렀을때 lottos에 저장된거 지워야함([]삭제없음)
				//[] -> ArrayList -> (삭제) -> ArrayList -> [] 바꿔야함
				List<Integer> lottosList = new ArrayList<Integer>(Arrays.asList(lotto)); //[] -> ArrayList
				lottosList.clear(); //ArrayList 내용삭제
				lotto = lottosList.toArray(new Integer[lottosList.size()]); //ArrayList -> []
//				System.out.println(Arrays.toString(lotto));
				//수동눌렀다가 삭제버튼눌렀을때 lottos에 저장된거 지워야함([]삭제없음)
				sudong.clear();
			}
		});

		pnlsu.setLayout(new GridLayout(0, 7)); // 7부터 자른다

		pnlhap.add(pnlbuttons);
		pnlhap.add(pnlsu);
		
		pnlhap.setLayout(new BoxLayout(pnlhap, BoxLayout.Y_AXIS)); // 세로정렬
		pnlhap.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		
		add(pnlhap);

	}

	public Integer[] getLotto() {
		return lotto;
	}

	public JRadioButton getRadio1() {
		return radio1;
	}


}