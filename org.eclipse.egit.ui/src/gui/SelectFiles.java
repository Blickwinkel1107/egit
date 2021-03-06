package gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import retro.Retro;

public class SelectFiles {

	private JFrame frmSelectFilesPath;
	private JTextField textOldVersion;
	private JTextField textNewVersion;
	private JTextField textReq;
	/*
	 * modified by YHR
	 */
	private String path_old;
	private String path_new;
	private String path_req;
	private String username;
	private static String MAINTAINER = "maintainer";

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SelectFiles window = new SelectFiles();
					window.frmSelectFilesPath.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectFiles() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// RetrievingDialog retriever = new RetrievingDialog();
		// retriever.hide();
		// retriever.show();
		// System.out.println(this.username);
		// System.out.println(this.password);
		frmSelectFilesPath = new JFrame();
		frmSelectFilesPath.setTitle("Select files path");
		frmSelectFilesPath.setBounds(100, 100, 655, 442);
		frmSelectFilesPath.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelectFilesPath.getContentPane().setLayout(null);

		textOldVersion = new JTextField();
		textOldVersion.setBounds(118, 99, 390, 19);
		frmSelectFilesPath.getContentPane().add(textOldVersion);
		textOldVersion.setColumns(10);

		textNewVersion = new JTextField();
		textNewVersion.setColumns(10);
		textNewVersion.setBounds(118, 145, 390, 19);
		frmSelectFilesPath.getContentPane().add(textNewVersion);

		textReq = new JTextField();
		textReq.setColumns(10);
		textReq.setBounds(118, 186, 390, 19);
		frmSelectFilesPath.getContentPane().add(textReq);

		JLabel lblOldVersion = new JLabel("Old version");
		lblOldVersion.setBounds(12, 94, 100, 29);
		frmSelectFilesPath.getContentPane().add(lblOldVersion);

		JLabel lblNewVersion = new JLabel("New version");
		lblNewVersion.setBounds(12, 135, 100, 29);
		frmSelectFilesPath.getContentPane().add(lblNewVersion);

		JLabel lblRequirement = new JLabel("Requirement");
		lblRequirement.setBounds(12, 181, 100, 29);
		frmSelectFilesPath.getContentPane().add(lblRequirement);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// retriever.show();
				// frmSelectFilesPath.setVisible(false);
				/*
				 * modified by YHR
				 */
				System.out.println(username);
				if (username.equals(MAINTAINER) == true) {
					// System.out.println("fuck");
					// yx: he xin dai ma :-)
					System.out.println("enter MaintainerWin");
					// frmSelectFilesPath.setVisible(false);
					String oldVerPath;
					String newVerPath;
					String reqPath;
					if (textOldVersion.getText().equals("")) {
						oldVerPath = "/home/perceptron/egit-master/git/egit/org.eclipse.egit.ui/data/sample/AquaLush_Change3";
						newVerPath = "/home/perceptron/egit-master/git/egit/org.eclipse.egit.ui/data/sample/AquaLush_Change4";
						reqPath = "/home/perceptron/egit-master/git/egit/org.eclipse.egit.ui/data/sample/AquaLush_Requirement";
					} else {
						oldVerPath = textOldVersion.getText();
						newVerPath = textNewVersion.getText();
						reqPath = textReq.getText();
					}

					Retro re = new Retro();

					// re.process();
					re.process(newVerPath, oldVerPath, reqPath);

					MaintainerWin maintainer = new MaintainerWin(re);
					maintainer.createPopupMenu();
					// retriever.close();
					frmSelectFilesPath.dispose();

				}
				frmSelectFilesPath.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnConfirm.setText("Retrieving requirements ...");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnConfirm.setText("Confirm");
			}
		});
		btnConfirm.setBounds(213, 336, 230, 25);
		frmSelectFilesPath.getContentPane().add(btnConfirm);

		JButton btnSelect = new JButton("Select..");
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * modified by YHR
			 */
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser("/Users/mac/Desktop");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					path_req = fileChooser.getSelectedFile().getAbsolutePath();// 杩欎釜灏辨槸浣犻�夋嫨鐨勬枃浠跺す鐨勮矾寰�
					System.out.println(path_req);
					textReq.setText(path_req);
				}
			}
		});

		btnSelect.setBounds(519, 183, 106, 25);
		frmSelectFilesPath.getContentPane().add(btnSelect);

		JButton button = new JButton("Select..");
		button.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * modified by YHR
			 */
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser("/Users/mac/Desktop");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					path_new = fileChooser.getSelectedFile().getAbsolutePath();// 杩欎釜灏辨槸浣犻�夋嫨鐨勬枃浠跺す鐨勮矾寰�
					System.out.println(path_new);
					textNewVersion.setText(path_new);
				}
			}
		});
		button.setBounds(519, 142, 106, 25);
		frmSelectFilesPath.getContentPane().add(button);

		JButton button_1 = new JButton("Select..");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * modified by YHR
			 */
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser("/Users/mac/Desktop");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					path_old = fileChooser.getSelectedFile().getAbsolutePath();// 杩欎釜灏辨槸浣犻�夋嫨鐨勬枃浠跺す鐨勮矾寰�
					System.out.println(path_old);
				}
				textOldVersion.setText(path_old);
			}
		});
		button_1.setBounds(519, 96, 106, 25);
		frmSelectFilesPath.getContentPane().add(button_1);
		frmSelectFilesPath.setVisible(true); // 鏄剧ず绐�?�?
	}

	protected void setPath_old(String path_old) {
		this.path_old = path_old;
	}

	protected void setPath_new(String path_new) {
		this.path_new = path_new;
	}

	protected void setPath_req(String path_req) {
		this.path_req = path_req;
	}
}
