package swing;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Calculadora apariencia de Windows
 * 
 * @author Gabriel González
 * @version 1.0
 * @contacto gabrielgonzalez1802@gmail.com
 */
public class Calculadora extends JFrame implements MouseListener, KeyListener, MouseMotionListener {

	private static final long serialVersionUID = 8693320697709176286L;
	private JPanel contentPane;
	private JButton btnFlechaLeft;
	private JButton btnCE;
	private JButton btnC;
	private JButton btnMm;
	private JButton btnRaiz;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btnDivision;
	private JButton btn4;
	private JButton btn1;
	private JButton btn2;
	private JButton btn0;
	private JButton btn3;
	private JButton btn5;
	private JButton btn6;
	private JButton btnSuma;
	private JButton btnResta;
	private JButton btnMultiplicacion;
	private JButton btnResultado;

	private JTextField tfPantalla;
	private JTextField tfTemp;

	private final String SUMA = "sumar";
	private final String RESTA = "restar";
	private final String MULTIPLICACION = "multiplicar";
	private final String DIVISION = "dividir";

	private String oldTemp = "";
	private String newTemp = "";
	private String opcion = "";
	private String operacion = "";
	private String pantallaArriba = "";
	private String pantallaTemporal = "";
	private String newValue = "";
	private String oldValue = "";
	private String opcionTemporal = "";
	private String signo = "";
	private String resultado = "";
	private String raizNumero =""; 
	private String memory = "";

	private int contSuma = 0;
	private int contResta = 0;
	private int contMultiplicacion = 0;
	private int contDivision = 0;
	private int longitud = 0;

	private boolean numero = false;
	private boolean bloqueo = false;
	private boolean reciproco = false;
	private boolean raiz = false;
	private boolean bloqueoFlecha = false;
	private boolean isDecimal = false;
	private boolean mActive = false;

	private JButton buttonComa;
	private JButton btn1x;
	private JButton btnPorcentaje;
	private JButton btnMC;
	private JButton btnMR;
	private JButton btnMS;
	private JButton btnMMas;
	private JButton btnMMenos;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JPanel panelPantalla;
	private JRadioButtonMenuItem rdbtnEstandar;
	private JMenu mnVer;
	private JRadioButtonMenuItem rdbtnCientfica;

	private Font standar = new Font("Tahoma", Font.PLAIN, 20);
	private Font smallFont = new Font("Dialog", Font.PLAIN, 13);
	private JTextField tfM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculadora frame = new Calculadora();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculadora() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Gabriel\\workspace\\practica\\Recursos\\calculador.png"));
		setTitle("Calculadora");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 221, 337);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnVer = new JMenu("Ver");
		mnVer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menuBar.add(mnVer);

		rdbtnEstandar = new JRadioButtonMenuItem("Est\u00E1ndar");
		rdbtnEstandar.setSelected(true);
		buttonGroup.add(rdbtnEstandar);
		mnVer.add(rdbtnEstandar);

		rdbtnCientfica = new JRadioButtonMenuItem("Cient\u00EDfica");
		buttonGroup.add(rdbtnCientfica);
		mnVer.add(rdbtnCientfica);

		JSeparator separator_1 = new JSeparator();
		mnVer.add(separator_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Historial");
		mnVer.add(mntmNewMenuItem);

		JMenuItem mntmNmeroDeDgitos = new JMenuItem("N\u00FAmero de d\u00EDgitos en grupo");
		mnVer.add(mntmNmeroDeDgitos);

		JSeparator separator_2 = new JSeparator();
		mnVer.add(separator_2);

		JRadioButtonMenuItem rdbtnmntmBsicas = new JRadioButtonMenuItem("B\u00E1sicas");
		rdbtnmntmBsicas.setSelected(true);
		buttonGroup_1.add(rdbtnmntmBsicas);
		mnVer.add(rdbtnmntmBsicas);

		JMenu mnEdicin = new JMenu("Edici\u00F3n");
		mnEdicin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menuBar.add(mnEdicin);

		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menuBar.add(mnAyuda);

		JMenuItem mntmVerLaAyuda = new JMenuItem("Ver la Ayuda");
		mnAyuda.add(mntmVerLaAyuda);

		JSeparator separator = new JSeparator();
		mnAyuda.add(separator);

		JMenuItem mntmAce = new JMenuItem("Acerca de Calculadora");
		mnAyuda.add(mntmAce);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelPantalla = new JPanel();
		panelPantalla.setBackground(Color.WHITE);
		panelPantalla.setBorder(new LineBorder(SystemColor.desktop));
		panelPantalla.setBounds(10, 11, 195, 71);
		contentPane.add(panelPantalla);
		panelPantalla.setLayout(null);

		tfTemp = new JTextField();
		tfTemp.setEditable(false);
		tfTemp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfTemp.setBackground(SystemColor.window);
		tfTemp.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTemp.setBounds(10, 11, 175, 18);
		panelPantalla.add(tfTemp);
		tfTemp.setColumns(10);

		tfPantalla = new JTextField();
		tfPantalla.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfPantalla.setBackground(SystemColor.window);
		tfPantalla.setBounds(20, 33, 165, 28);
		panelPantalla.add(tfPantalla);
		tfPantalla.setFont(standar);
		tfPantalla.setText("0");
		tfPantalla.setEditable(false);
		tfPantalla.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPantalla.setColumns(10);
		
		tfM = new JTextField();
		tfM.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfM.setBounds(10, 40, 14, 20);
		panelPantalla.add(tfM);
		tfM.setColumns(10);

		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(SystemColor.inactiveCaption);
		panelBotones.setBounds(10, 81, 199, 207);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);

		btnMC = new JButton("MC");
		btnMC.setMargin(new Insets(0, 0, 0, 0));
		btnMC.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnMC.setBounds(0, 11, 37, 26);
		panelBotones.add(btnMC);

		btnMR = new JButton("MR");
		btnMR.setMargin(new Insets(0, 0, 0, 0));
		btnMR.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnMR.setBounds(40, 11, 36, 26);
		panelBotones.add(btnMR);

		btnMS = new JButton("MS");
		btnMS.setMargin(new Insets(0, 0, 0, 0));
		btnMS.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnMS.setBounds(78, 11, 37, 26);
		panelBotones.add(btnMS);

		btnMMas = new JButton("M+");
		btnMMas.setMargin(new Insets(0, 0, 0, 0));
		btnMMas.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnMMas.setBounds(118, 11, 37, 26);
		panelBotones.add(btnMMas);

		btnMMenos = new JButton("M-");
		btnMMenos.setMargin(new Insets(0, 0, 0, 0));
		btnMMenos.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnMMenos.setBounds(158, 11, 38, 26);
		panelBotones.add(btnMMenos);

		btnFlechaLeft = new JButton("<-");
		btnFlechaLeft.setMargin(new Insets(0, 0, 0, 0));
		btnFlechaLeft.setFont(new Font("Arial", Font.BOLD, 16));
		btnFlechaLeft.setBounds(0, 43, 37, 28);
		panelBotones.add(btnFlechaLeft);

		btnCE = new JButton("CE");
		btnCE.setMargin(new Insets(0, 0, 0, 0));
		btnCE.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnCE.setBounds(40, 43, 36, 28);
		panelBotones.add(btnCE);

		btnC = new JButton("C");
		btnC.setMargin(new Insets(0, 0, 0, 0));
		btnC.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnC.setBounds(78, 43, 37, 28);
		panelBotones.add(btnC);

		btnMm = new JButton("+/-");
		btnMm.setMargin(new Insets(0, 0, 0, 0));
		btnMm.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnMm.setBounds(118, 43, 37, 28);
		panelBotones.add(btnMm);

		btnRaiz = new JButton("Raiz");
		btnRaiz.setMargin(new Insets(0, 0, 0, 0));
		btnRaiz.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnRaiz.setBounds(158, 43, 38, 28);
		panelBotones.add(btnRaiz);

		btn7 = new JButton("7");
		btn7.setMargin(new Insets(0, 0, 0, 0));
		btn7.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn7.setBounds(0, 75, 37, 28);
		panelBotones.add(btn7);

		btn8 = new JButton("8");
		btn8.setMargin(new Insets(0, 0, 0, 0));
		btn8.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn8.setBounds(40, 75, 36, 28);
		panelBotones.add(btn8);

		btn9 = new JButton("9");
		btn9.setMargin(new Insets(0, 0, 0, 0));
		btn9.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn9.setBounds(78, 75, 37, 28);
		panelBotones.add(btn9);

		btnDivision = new JButton("/");
		btnDivision.setMargin(new Insets(0, 0, 0, 0));
		btnDivision.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnDivision.setBounds(118, 75, 37, 28);
		panelBotones.add(btnDivision);

		btnPorcentaje = new JButton("%");
		btnPorcentaje.setMargin(new Insets(0, 0, 0, 0));
		btnPorcentaje.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnPorcentaje.setBounds(158, 75, 38, 28);
		panelBotones.add(btnPorcentaje);

		btn4 = new JButton("4");
		btn4.setMargin(new Insets(0, 0, 0, 0));
		btn4.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn4.setBounds(0, 107, 37, 27);
		panelBotones.add(btn4);

		btn5 = new JButton("5");
		btn5.setMargin(new Insets(0, 0, 0, 0));
		btn5.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn5.setBounds(40, 107, 36, 27);
		panelBotones.add(btn5);

		btn6 = new JButton("6");
		btn6.setMargin(new Insets(0, 0, 0, 0));
		btn6.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn6.setBounds(78, 107, 37, 27);
		panelBotones.add(btn6);

		btnMultiplicacion = new JButton("*");
		btnMultiplicacion.setMargin(new Insets(0, 0, 0, 0));
		btnMultiplicacion.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnMultiplicacion.setBounds(118, 107, 37, 27);
		panelBotones.add(btnMultiplicacion);

		btn1x = new JButton("1/x");
		btn1x.setMargin(new Insets(0, 0, 0, 0));
		btn1x.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn1x.setBounds(158, 107, 38, 27);
		panelBotones.add(btn1x);

		btn1 = new JButton("1");
		btn1.setMargin(new Insets(0, 0, 0, 0));
		btn1.setSelectedIcon(null);
		btn1.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn1.setBounds(0, 138, 37, 25);
		panelBotones.add(btn1);

		btn2 = new JButton("2");
		btn2.setMargin(new Insets(0, 0, 0, 0));
		btn2.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn2.setBounds(40, 138, 36, 25);
		panelBotones.add(btn2);

		btn3 = new JButton("3");
		btn3.setMargin(new Insets(0, 0, 0, 0));
		btn3.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn3.setBounds(78, 138, 37, 25);
		panelBotones.add(btn3);

		btnResta = new JButton("-");
		btnResta.setMargin(new Insets(0, 0, 0, 0));
		btnResta.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnResta.setBounds(118, 138, 37, 25);
		panelBotones.add(btnResta);

		btnResultado = new JButton("=");
		btnResultado.setMargin(new Insets(0, 0, 0, 0));
		btnResultado.setFont(new Font("Arial", Font.BOLD, 16));
		btnResultado.setBounds(158, 137, 38, 59);
		panelBotones.add(btnResultado);

		btn0 = new JButton("0");
		btn0.setMargin(new Insets(0, 0, 0, 0));
		btn0.setFont(new Font("Verdana", Font.PLAIN, 16));
		btn0.setBounds(0, 167, 76, 29);
		panelBotones.add(btn0);

		buttonComa = new JButton(",");
		buttonComa.setMargin(new Insets(0, 0, 0, 0));
		buttonComa.setFont(new Font("Verdana", Font.PLAIN, 16));
		buttonComa.setBounds(79, 167, 36, 29);
		panelBotones.add(buttonComa);

		btnSuma = new JButton("+");
		btnSuma.setMargin(new Insets(0, 0, 0, 0));
		btnSuma.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnSuma.setBounds(118, 167, 38, 29);
		panelBotones.add(btnSuma);

		// agregarActionListener();
		agregarMouseListener();
		agregarKeyListener();
		agregarMouseMoved();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		pantallaArriba = "";
		oldTemp = oldValue;
		if (tfPantalla.getText().length() > 1) {
			oldValue = oldTemp;
		}
		oldTemp = oldValue;

		if((!reciproco) || (!raiz)) {
			validarLongitud(1);
		}
		
		if(tfPantalla.getText().length()>15) {
			validarLongitud(500);
		}

		// NÚMERO 1
		if (e.getSource() == btn1) {
			if (bloqueo == false) {
				numero(true);
				if (!opcion.equals("")) {
					if (!tfTemp.equals(null)) {
						validarLongitud(1);
						oldValue = tfPantalla.getText();
						tfPantalla.setText("1");
						newValue = tfPantalla.getText();
						opcion = "";
					}
				} else {
					if (tfPantalla.getText().equals("0")) {
						oldTemp = "";
					}
					tfPantalla.setText(concatenarDato("1"));
				}
			}
		}

		// NÚMERO 2
		if (e.getSource() == btn2) {
			if (bloqueo == false) {
				numero(true);
				if (!opcion.equals("")) {
					if (!tfTemp.equals(null)) {
						validarLongitud(1);
						oldValue = tfPantalla.getText();
						tfPantalla.setText("2");
						newValue = tfPantalla.getText();
						opcion = "";
					}
				} else {
					if (tfPantalla.getText().equals("0")) {
						oldTemp = "";
					}
					tfPantalla.setText(concatenarDato("2"));
				}
			}
		}

		// NÚMERO 3
		if (e.getSource() == btn3) {
			if (bloqueo == false) {
				numero(true);
				if (!opcion.equals("")) {
					if (!tfTemp.equals(null)) {
						validarLongitud(1);
						oldValue = tfPantalla.getText();
						tfPantalla.setText("3");
						newValue = tfPantalla.getText();
						opcion = "";
					}
				} else {
					if (tfPantalla.getText().equals("0")) {
						oldTemp = "";
					}
					tfPantalla.setText(concatenarDato("3"));
				}
			}
		}

		// NÚMERO 4
		if (e.getSource() == btn4) {
			if (bloqueo == false) {
				numero(true);
				if (!opcion.equals("")) {
					if (!tfTemp.equals(null)) {
						validarLongitud(1);
						oldValue = tfPantalla.getText();
						tfPantalla.setText("4");
						newValue = tfPantalla.getText();
						opcion = "";
					}
				} else {
					if (tfPantalla.getText().equals("0")) {
						oldTemp = "";
					}
					tfPantalla.setText(concatenarDato("4"));
				}
			}
		}

		// NÚMERO 5
		if (e.getSource() == btn5) {
			if (bloqueo == false) {
				numero(true);
				if (!opcion.equals("")) {
					if (!tfTemp.equals(null)) {
						validarLongitud(1);
						oldValue = tfPantalla.getText();
						tfPantalla.setText("5");
						newValue = tfPantalla.getText();
						opcion = "";
					}
				} else {
					if (tfPantalla.getText().equals("0")) {
						oldTemp = "";
					}
					tfPantalla.setText(concatenarDato("5"));
				}
			}
		}

		// NÚMERO 6
		if (e.getSource() == btn6) {
			if (bloqueo == false) {
				numero(true);
				if (!opcion.equals("")) {
					if (!tfTemp.equals(null)) {
						validarLongitud(1);
						oldValue = tfPantalla.getText();
						tfPantalla.setText("6");
						newValue = tfPantalla.getText();
						opcion = "";
					}
				} else {
					if (tfPantalla.getText().equals("0")) {
						oldTemp = "";
					}
					tfPantalla.setText(concatenarDato("6"));
				}
			}
		}

		// NÚMERO 7
		if (e.getSource() == btn7) {
			if (bloqueo == false) {
				numero(true);
				if (!opcion.equals("")) {
					if (!tfTemp.equals(null)) {
						validarLongitud(1);
						oldValue = tfPantalla.getText();
						tfPantalla.setText("7");
						newValue = tfPantalla.getText();
						opcion = "";
					}
				} else {
					if (tfPantalla.getText().equals("0")) {
						oldTemp = "";
					}
					tfPantalla.setText(concatenarDato("7"));
				}
			}
		}

		// NÚMERO 8
		if (e.getSource() == btn8) {
			if (bloqueo == false) {
				numero(true);
				if (!opcion.equals("")) {
					if (!tfTemp.equals(null)) {
						validarLongitud(1);
						oldValue = tfPantalla.getText();
						tfPantalla.setText("8");
						newValue = tfPantalla.getText();
						opcion = "";
					}
				} else {
					if (tfPantalla.getText().equals("0")) {
						oldTemp = "";
					}
					tfPantalla.setText(concatenarDato("8"));
				}
			}
		}

		// NÚMERO 9
		if (e.getSource() == btn9) {
			if (bloqueo == false) {
				numero(true);
				if (!opcion.equals("")) {
					if (!tfTemp.equals(null)) {
						validarLongitud(1);
						oldValue = tfPantalla.getText();
						tfPantalla.setText("9");
						newValue = tfPantalla.getText();
						opcion = "";
					}
				} else {
					if (tfPantalla.getText().equals("0")) {
						oldTemp = "";
					}
					tfPantalla.setText(concatenarDato("9"));
				}
			}
		}

		// NÚMERO 0
		if (e.getSource() == btn0) {
			if (bloqueo == false) {
				numero(true);
				if (!opcion.equals("")) {
					if (!tfTemp.equals(null)) {
						validarLongitud(1);
						oldValue = tfPantalla.getText();
						tfPantalla.setText("0");
						newValue = tfPantalla.getText();
						opcion = "";
					}
				} else {
					if (tfPantalla.getText().equals("0")) {
						oldTemp = "";
					}
					tfPantalla.setText(concatenarDato("0"));
				}
			}
		}

		// Boton +
		if (e.getSource() == btnSuma) {
			if (bloqueo == false) {
				if ((numero == true || tfPantalla.getText().equals("0")) || mActive && !opcionTemporal.equals(SUMA)) {

					contSuma++;

					pantallaArriba = tfTemp.getText();

					if (opcionTemporal.equals(RESTA)) {
						opcionTemporal = RESTA;
					} else if (opcionTemporal.equals(SUMA) || opcionTemporal.equals("")) {
						opcionTemporal = SUMA;
					} else if (opcionTemporal.equals(MULTIPLICACION)) {
						opcionTemporal = MULTIPLICACION;
					} else if (opcionTemporal.equals(DIVISION)) {
						opcionTemporal = DIVISION;
					}

					if (!oldTemp.equals("0")) {
						oldTemp = newValue;
					}

					if (oldTemp.length() < 2) {
						if (newValue.length() > 1) {
							oldValue = "";
						}
					}

					// Efectua las operaciones correspondiente que vengan en memoria
					if (opcionTemporal.equals(RESTA)) {
						resultado = String.valueOf(restar(oldValue, newValue));
						resultado = resultado.replaceFirst("[.]0", "");
						validarLongitud(resultado.length());
						tfPantalla.setText(resultado);
					} else if (opcionTemporal.equals(SUMA)) {
						resultado = String.valueOf(sumar(oldValue, newValue));
						resultado = resultado.replaceFirst("[.]0", "");
						validarLongitud(resultado.length());
						tfPantalla.setText(resultado);
					} else if (opcionTemporal.equals(MULTIPLICACION)) {
						if (!oldValue.equals("0") && !oldValue.equals("")) {
							resultado = String.valueOf(multiplicar(oldValue, newValue));
							resultado = resultado.replaceFirst("[.]0", "");
							validarLongitud(resultado.length());
							tfPantalla.setText(resultado);
						}
					} else if (opcionTemporal.equals(DIVISION)) {
						if (!oldValue.equals("") && !oldValue.equals("0")) {
							resultado = String.valueOf(dividir(oldValue, newValue));
							resultado = resultado.replaceFirst("[.]0", "");
							validarLongitud(resultado.length());
							tfPantalla.setText(resultado);
						}
					}

					oldValue = oldTemp;

					// Verifica el estado de los contadores para poder asignar el signo que tendra
					// la pantallaArriba
					if (contSuma > 0) {
						signo = "+";
						contResta = 0;
						contMultiplicacion = 0;
						contDivision = 0;
					} else {

						if (contResta > 0) {
							signo = "-";
							contSuma = 0;
							contMultiplicacion = 0;
							contDivision = 0;
						}

						if (contMultiplicacion > 0) {
							signo = "*";
							contResta = 0;
							contSuma = 0;
							contDivision = 0;
						}

						if (contDivision > 0) {
							signo = "/";
							contResta = 0;
							contSuma = 0;
							contMultiplicacion = 0;
						}
					}

					if (tfPantalla.getText().equals("0")) {
						if (opcionTemporal.equals(RESTA)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(SUMA)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(MULTIPLICACION)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(DIVISION)) {
							pantallaArriba = "0 " + signo;
						}
						tfTemp.setText(pantallaArriba);
						newTemp = newValue;
					} else {
						if (!opcionTemporal.equals("")) {
							if (oldValue.equals("0")) {
								if ((reciproco) || (raiz)) {
									pantallaArriba += signo;
									reciproco = false;
									raiz = false;
								} else {
									pantallaArriba += " " + newValue + " " + signo;
								}
							} else {
								if ((reciproco) || (raiz)) {
									pantallaArriba += signo;
									reciproco = false;
									raiz = false;
								} else {
									pantallaArriba += " " + oldValue + " " + signo;
								}
							}
						}

						tfTemp.setText(pantallaArriba);
						newTemp = newValue;
					}

				} else {
					if (opcionTemporal!=SUMA) {
						pantallaArriba = tfTemp.getText().substring(0, tfTemp.getText().length() - 1);
						pantallaArriba = pantallaArriba + "+";
						tfTemp.setText(pantallaArriba);
						pantallaTemporal = pantallaArriba;
					}
				}

				// Asigna la SUMA para que quede en memoria
				opcionTemporal = SUMA;
				opcion = SUMA;
				operacion = SUMA;
				numero(false);
			}
		}

		// Boton -
		if (e.getSource() == btnResta) {
			if (bloqueo == false) {
				if ((numero == true || tfPantalla.getText().equals("0")) || mActive && !opcionTemporal.equals(RESTA)) {

					contResta++;

					pantallaArriba = tfTemp.getText();

					if (opcionTemporal.equals(RESTA) || opcionTemporal.equals("")) {
						opcionTemporal = RESTA;
					} else if (opcionTemporal.equals(SUMA)) {
						opcionTemporal = SUMA;
					} else if (opcionTemporal.equals(MULTIPLICACION)) {
						opcionTemporal = MULTIPLICACION;
					} else if (opcionTemporal.equals(DIVISION)) {
						opcionTemporal = DIVISION;
					}

					if (!oldTemp.equals("0")) {
						oldTemp = newValue;
						newTemp = "";
					}

					if (oldTemp.length() < 2) {
						if (newValue.length() > 1) {
							oldValue = "";
						}
					}

					// Efectua las operaciones correspondiente que vengan en memoria
					if (opcionTemporal.equals(RESTA)) {
						if(oldValue.equals("")) {
							resultado = String.valueOf(restar(oldTemp, newTemp));
						}else {
							resultado = String.valueOf(restar(oldValue, newValue));	
						}
						resultado = resultado.replaceFirst("[.]0", "");
						validarLongitud(resultado.length());
						tfPantalla.setText(resultado);
					} else if (opcionTemporal.equals(SUMA)) {
						resultado = String.valueOf(sumar(oldValue, newValue));
						resultado = resultado.replaceFirst("[.]0", "");
						validarLongitud(resultado.length());
						tfPantalla.setText(resultado);
					} else if (opcionTemporal.equals(MULTIPLICACION)) {
						if (!oldValue.equals("") && !oldValue.equals("0")) {
							resultado = String.valueOf(multiplicar(oldValue, newValue));
							resultado = resultado.replaceFirst("[.]0", "");
							validarLongitud(resultado.length());
							tfPantalla.setText(resultado);
						}
					} else if (opcionTemporal.equals(DIVISION)) {
						if (!oldValue.equals("") && !oldValue.equals("0")) {
							resultado = String.valueOf(dividir(oldValue, newValue));
							resultado = resultado.replaceFirst("[.]0", "");
							validarLongitud(resultado.length());
							tfPantalla.setText(resultado);
						}
					}

					oldValue = oldTemp;

					if (contResta > 0) {
						signo = "-";
						contSuma = 0;
						contMultiplicacion = 0;
						contDivision = 0;
					} else {

						if (contSuma > 0) {
							signo = "+";
							contResta = 0;
							contMultiplicacion = 0;
							contDivision = 0;
						}

						if (contMultiplicacion > 0) {
							signo = "*";
							contResta = 0;
							contSuma = 0;
							contDivision = 0;
						}

						if (contDivision > 0) {
							signo = "/";
							contResta = 0;
							contSuma = 0;
							contMultiplicacion = 0;
						}

					}

					if (tfPantalla.getText().equals("0")) {
						if (opcionTemporal.equals(RESTA)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(SUMA)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(DIVISION)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(MULTIPLICACION)) {
							pantallaArriba = "0 " + signo;
						}
						tfTemp.setText(pantallaArriba);
						newTemp = newValue;
					} else {
						if (!opcionTemporal.equals("")) {
							if (oldValue.equals("0")) {
								if ((reciproco) || (raiz)) {
									pantallaArriba += signo;
									reciproco = false;
									raiz = false;
								} else {
									pantallaArriba += " " + newValue + " " + signo;

								}
							} else {
								if ((reciproco) || (raiz))  {
									pantallaArriba += signo;
									reciproco = false;
									raiz = false;
								} else {
									pantallaArriba += " " + oldValue + " " + signo;
								}
							}
						}

						tfTemp.setText(pantallaArriba);
						newTemp = newValue;
					}

				}else {
					if(opcionTemporal!=RESTA) {
						pantallaArriba=tfTemp.getText().substring(0, tfTemp.getText().length()-1);
						pantallaArriba=pantallaArriba + "-";
						tfTemp.setText(pantallaArriba);
						pantallaTemporal = pantallaArriba;
					}
				}
				opcionTemporal = RESTA;
				operacion = RESTA;
				opcion = RESTA;
				numero(false);
			}
		}

		// Boton *
		if (e.getSource() == btnMultiplicacion) {
			if (bloqueo == false) {
				if ((numero == true || tfPantalla.getText().equals("0")) || mActive && !opcionTemporal.equals(MULTIPLICACION)) {

					contMultiplicacion++;

					pantallaArriba = tfTemp.getText();

					if (opcionTemporal.equals(RESTA)) {
						opcionTemporal = RESTA;
					} else if (opcionTemporal.equals(SUMA)) {
						opcionTemporal = SUMA;
					} else if (opcionTemporal.equals(MULTIPLICACION) || opcionTemporal.equals("")) {
						opcionTemporal = MULTIPLICACION;
					} else if (opcionTemporal.equals(DIVISION)) {
						opcionTemporal = DIVISION;
					}

					if (!oldTemp.equals("0")) {
						oldTemp = newValue;
					}

					if (oldTemp.length() < 2) {
						if (newValue.length() > 1) {
							oldValue = "";
						}
					}

					// Efectua las operaciones correspondiente que vengan en memoria
					if (opcionTemporal.equals(RESTA)) {
						resultado = String.valueOf(restar(oldValue, newValue));
						resultado = resultado.replaceFirst("[.]0", "");
						validarLongitud(resultado.length());
						tfPantalla.setText(resultado);
					} else if (opcionTemporal.equals(SUMA)) {
						resultado = String.valueOf(sumar(oldValue, newValue));
						resultado = resultado.replaceFirst("[.]0", "");
						validarLongitud(resultado.length());
						tfPantalla.setText(resultado);
					} else if (opcionTemporal.equals(MULTIPLICACION)) {
						if (!oldValue.equals("") && !oldValue.equals("0")) {
							resultado = String.valueOf(multiplicar(oldValue, newValue));
							resultado = resultado.replaceFirst("[.]0", "");
							validarLongitud(resultado.length());
							tfPantalla.setText(resultado);
						}
					} else if (opcionTemporal.equals(DIVISION)) {
						if (!oldValue.equals("") && !oldValue.equals("0")) {
							resultado = String.valueOf(dividir(oldValue, newValue));
							resultado = resultado.replaceFirst("[.]0", "");
							validarLongitud(resultado.length());
							tfPantalla.setText(resultado);
						}
					}

					oldValue = oldTemp;

					// Verifica el estado de los contadores para poder asignar el signo que tendra
					// la pantallaArriba
					if (contMultiplicacion > 0) {
						signo = "*";
						contResta = 0;
						contSuma = 0;
						contDivision = 0;
					} else {

						if (contResta > 0) {
							signo = "-";
							contSuma = 0;
							contMultiplicacion = 0;
							contDivision = 0;
						}

						if (contSuma > 0) {
							signo = "+";
							contResta = 0;
							contMultiplicacion = 0;
							contDivision = 0;
						}

						if (contDivision > 0) {
							signo = "/";
							contResta = 0;
							contSuma = 0;
							contMultiplicacion = 0;
						}
					}

					if (tfPantalla.getText().equals("0")) {
						if (opcionTemporal.equals(RESTA)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(SUMA)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(MULTIPLICACION)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(DIVISION)) {
							pantallaArriba = "0 " + signo;
						}
						tfTemp.setText(pantallaArriba);
						newTemp = newValue;
					} else {
						if (!opcionTemporal.equals("")) {
							if (oldValue.equals("0")) {
								if ((reciproco) || (raiz)) {
									pantallaArriba += signo;
									reciproco = false;
									raiz = false;
								} else {
									pantallaArriba += " " + newValue + " " + signo;
								}
							} else {
								if ((reciproco) || (raiz)) {
									pantallaArriba += signo;
									reciproco = false;
									raiz = false;
								} else {
									pantallaArriba += " " + oldValue + " " + signo;
								}
							}
						}

						tfTemp.setText(pantallaArriba);
						newTemp = newValue;
					}

				}else {
					if(opcionTemporal!=MULTIPLICACION) {
						pantallaArriba=tfTemp.getText().substring(0, tfTemp.getText().length()-1);
						pantallaArriba=pantallaArriba + "*";
						tfTemp.setText(pantallaArriba);
						pantallaTemporal = pantallaArriba;
					}
				}

				// Asigna la multiplicacion para que quede en memoria
				opcionTemporal = MULTIPLICACION;
				opcion = MULTIPLICACION;
				operacion = MULTIPLICACION;
				numero(false);
			}
		}

		// Boton /
		if (e.getSource() == btnDivision) {
			if (bloqueo == false) {
				if ((numero == true || tfPantalla.getText().equals("0")) || mActive && !opcionTemporal.equals(DIVISION)) {

					contDivision++;

					pantallaArriba = tfTemp.getText();

					if (opcionTemporal.equals(RESTA)) {
						opcionTemporal = RESTA;
					} else if (opcionTemporal.equals(SUMA)) {
						opcionTemporal = SUMA;
					} else if (opcionTemporal.equals(MULTIPLICACION)) {
						opcionTemporal = MULTIPLICACION;
					} else if (opcionTemporal.equals(DIVISION) || opcionTemporal.equals("")) {
						opcionTemporal = DIVISION;
					}

					if (!oldTemp.equals("0")) {
						oldTemp = newValue;
					}

					if (oldTemp.length() < 2) {
						if (newValue.length() > 1) {
							oldValue = "";
						}
					}

					// Efectua las operaciones correspondiente que vengan en memoria
					if (opcionTemporal.equals(RESTA)) {
						resultado = String.valueOf(restar(oldValue, newValue));
						resultado = resultado.replaceFirst("[.]0", "");
						validarLongitud(resultado.length());
						tfPantalla.setText(resultado);
					} else if (opcionTemporal.equals(SUMA)) {
						resultado = String.valueOf(sumar(oldValue, newValue));
						resultado = resultado.replaceFirst("[.]0", "");
						validarLongitud(resultado.length());
						tfPantalla.setText(resultado);
					} else if (opcionTemporal.equals(MULTIPLICACION)) {
						if (!oldValue.equals("") && !oldValue.equals("0")) {
							resultado = String.valueOf(multiplicar(oldValue, newValue));
							resultado = resultado.replaceFirst("[.]0", "");
							validarLongitud(resultado.length());
							tfPantalla.setText(resultado);
						}
					} else if (opcionTemporal.equals(DIVISION)) {
						if (!oldValue.equals("") && !oldValue.equals("0")) {
							resultado = String.valueOf(dividir(oldValue, newValue));
							resultado = resultado.replaceFirst("[.]0", "");
							validarLongitud(resultado.length());
							tfPantalla.setText(resultado);
						}
					}

					oldValue = oldTemp;

					// Verifica el estado de los contadores para poder asignar el signo que tendra
					// la pantallaArriba
					if (contDivision > 0) {
						signo = "/";
						contResta = 0;
						contSuma = 0;
						contMultiplicacion = 0;
					} else {

						if (contResta > 0) {
							signo = "-";
							contSuma = 0;
							contMultiplicacion = 0;
							contDivision = 0;
						}

						if (contSuma > 0) {
							signo = "+";
							contResta = 0;
							contMultiplicacion = 0;
							contDivision = 0;
						}

						if (contMultiplicacion > 0) {
							signo = "*";
							contResta = 0;
							contSuma = 0;
							contDivision = 0;
						}
					}

					if (tfPantalla.getText().equals("0")) {
						if (opcionTemporal.equals(RESTA)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(SUMA)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(MULTIPLICACION)) {
							pantallaArriba = "0 " + signo;
						} else if (opcionTemporal.equals(DIVISION)) {
							pantallaArriba = "0 " + signo;
						}
						tfTemp.setText(pantallaArriba);
						newTemp = newValue;
					} else {
						if (!opcionTemporal.equals("")) {
							if (oldValue.equals("0")) {
								if ((reciproco) || (raiz)) {
									pantallaArriba += signo;
									reciproco = false;
									raiz = false;
								} else {
									pantallaArriba += " " + newValue + " " + signo;
								}
							} else {
								if ((reciproco) || (raiz)) {
									pantallaArriba += signo;
									reciproco = false;
									raiz = false;
								} else {
									pantallaArriba += " " + oldValue + " " + signo;
								}
							}
						}

						tfTemp.setText(pantallaArriba);
						newTemp = newValue;
					}

				}else {
					if(opcionTemporal!=DIVISION) {
						pantallaArriba=tfTemp.getText().substring(0, tfTemp.getText().length()-1);
						pantallaArriba=pantallaArriba + "/";
						tfTemp.setText(pantallaArriba);
						pantallaTemporal = pantallaArriba;
					}
				}

				// Asigna opcion de la DIVISION para que quede en memoria
				opcionTemporal = DIVISION;
				opcion = DIVISION;
				operacion = DIVISION;
				numero(false);
			}
		}

		// Boton =
		if (e.getSource() == btnResultado) {
			if (bloqueo == false) {
				tfTemp.setText("");
				// System.out.println("Valor de la operacion = " + operacion);
				switch (operacion) {
				case SUMA:
					resultado = String.valueOf(sumar(oldValue, newValue));
					resultado = resultado.replaceFirst("[.]0", "");
					newValue = resultado;
					validarLongitud(resultado.length());
					tfPantalla.setText(newValue);
					oldValue = "0";
					break;
				case RESTA:
					resultado = String.valueOf(restar(oldValue, newValue));
					resultado = resultado.replaceFirst("[.]0", "");
					newValue = resultado;
					validarLongitud(resultado.length());
					tfPantalla.setText(newValue);
					oldValue = "0";
					break;
				case MULTIPLICACION:
					resultado = String.valueOf(multiplicar(oldValue, newValue));
					resultado = resultado.replaceFirst("[.]0", "");
					newValue = resultado;
					validarLongitud(resultado.length());
					tfPantalla.setText(newValue);
					oldValue = "0";
					break;
				case DIVISION:
					resultado = String.valueOf(dividir(oldValue, newValue));
					resultado = resultado.replaceFirst("[.]0", "");
					newValue = resultado;
					validarLongitud(resultado.length());
					tfPantalla.setText(newValue);
					oldValue = "0";
					break;
				default:
					break;
				}
			}
		}

		if (e.getSource() == btnC) {
			bloqueo = false;
			limpiarC();
		}

		if (e.getSource() == btnCE) {
			bloqueo = false;
			limpiarCE();
		}

		if (e.getSource() == btnFlechaLeft) {
			if (bloqueo == false && bloqueoFlecha == false) {
				regresaNumero();
			}
		}

		if (e.getSource() == btnMm) {
			if (bloqueo == false) {
				signoContrario();
			}
		}

		if (e.getSource() == btn1x) {
			if (bloqueo == false) {
				String reciproco = reciproco(newValue);
				tfPantalla.setText(reciproco);
				newValue = reciproco;
				bloqueoFlecha = true;
			}
		}
		
		if(e.getSource() == btnRaiz) {
			if (bloqueo == false) {
				raizNumero = raiz(newValue);
				tfPantalla.setText(raizNumero);
				newValue = raizNumero;
				bloqueoFlecha = true;
			}
		}
		
		if(e.getSource() == btnPorcentaje) {
			if (bloqueo == false) {
				String porcentaje = porcentaje(oldValue, newValue);
				tfPantalla.setText(porcentaje);
				newValue = porcentaje;
			}
		}
		
		if(e.getSource() == buttonComa) {
			decimal();
		}
		
		if(e.getSource() == btnMS) {
			mStorage(tfPantalla.getText());
		}
		
		if(e.getSource() == btnMR) {
			mRecall();
		}
		
		if(e.getSource() == btnMC) {
			mClear();
		}
		
		if(e.getSource() == btnMMas) {
			mMas(tfPantalla.getText());
		}
		
		if(e.getSource() == btnMMenos) {
			mMenos(tfPantalla.getText());
		}

		if (e.getSource() == rdbtnEstandar) {
			if (rdbtnEstandar.isSelected()) {
				System.out.println("Modo estandar");
			}
		}

		if (e.getSource() == rdbtnCientfica) {
			if (rdbtnCientfica.isSelected()) {
				System.out.println("Modo Científico");
			}
		}

	}

	/**
	 * Método encargado de Sumar los valores almacenados en oldTemp + newTemp
	 * 
	 * @param oldTemp
	 * @param newTemp
	 * @return suma es el resultado de sumar oldTemp + newTemp
	 */
	public String sumar(String oldTemp, String newTemp) {
		double n1 = 0;
		double n2 = 0;
			
		if (oldTemp.equals("0") || oldTemp.equals("")) {
			oldValue = newTemp;
			n1 = Double.parseDouble("0");
		} else {
			n1 = Double.parseDouble(oldTemp.replace(',','.'));
		}

		if (newTemp.equals("")) {
			n2 = Double.parseDouble("0");
		} else {
			n2 = Double.parseDouble(newTemp.replace(',','.'));
		}

		double suma = n1 + n2;
		resultado = String.valueOf(suma);
		resultado = resultado.replaceFirst("[.]0", "").replace('.',',');
		
		return resultado;

	}

	/**
	 * Método encargado de Restar los valores almacenados en oldTemp - newTemp
	 * 
	 * @param oldTemp
	 * @param newTemp
	 * @return resultado devuelve la resta de oldTemp - newTemp
	 */
	public String restar(String oldTemp, String newTemp) {
		double n1 = 0;
		double n2 = 0;
		double resta;
		
		if (oldTemp.equals("0") || oldTemp.equals("")) {
			oldValue = newTemp;
			n1 = Double.parseDouble("0");
		} else {
			n1 = Double.parseDouble(oldTemp.replace(',','.'));
		}

		if (newTemp.equals("")) {
			n2 = Integer.parseInt("0");
		} else {
			n2 = Double.parseDouble(newTemp.replace(',','.'));
		}
		
		resta = n1 - n2;
		
		resultado = String.valueOf(resta);
		resultado = resultado.replaceFirst("[.]0", "").replace('.',',');

		return resultado;

	}

	/**
	 * Método encargado de multiplicar los valores almacenados en oldTemp * newTemp
	 * 
	 * @param oldTemp
	 * @param newTemp
	 * @return resultado devuelve el roducto del oldTemp * newTemp
	 */
	public String multiplicar(String oldTemp, String newTemp) {
		double n1 = 0;
		double n2 = 0;
		
		if (oldTemp.equals("0") || oldTemp.equals("")) {
			oldValue = newTemp;
			n1 = Double.parseDouble("0");
		} else {
			n1 = Double.parseDouble(oldTemp.replace(',','.'));
		}

		if (newTemp.equals("")) {
			n2 = Double.parseDouble("0");
		} else {
			n2 = Double.parseDouble(newTemp.replace(',','.'));
		}

		double producto = n1 * n2;
		if(producto==-0) {
			producto=0;
		}
		
		resultado = String.valueOf(producto);
		resultado = resultado.replaceFirst("[.]0", "").replace('.',',');
		
		return resultado;

	}

	/**
	 * Método encargado de dividir los valores almacenados en oldTemp / newTemp
	 * 
	 * @param oldTemp
	 * @param newTemp
	 * @return resultado devuelvede la dividicion de oldTemp / newTemp
	 */
	public String dividir(String oldTemp, String newTemp) {
		double n1 = 0;
		double n2 = 0;
		
		if (oldTemp.equals("0") || oldTemp.equals("")) {
			oldValue = newTemp;
			n1 = Double.parseDouble("0");
		} else {
			n1 = Double.parseDouble(oldTemp.replace(',','.'));
		}

		if (newTemp.equals("")) {
			n2 = Double.parseDouble("0");
		} else {
			n2 = Double.parseDouble(newTemp.replace(',','.'));
		}
		if (n1 == 0 && n2 == 0) {
			tfPantalla.setFont(smallFont);
			bloqueo = true;
			return "Resultado indefinido";
		} else if (n2 == 0) {
			tfPantalla.setFont(smallFont);
			bloqueo = true;
			return "No se Puede dividir entre cero";
		} else {

			double division = n1 / n2;
			resultado = String.valueOf(division);
			resultado = resultado.replaceFirst("[.]0", "").replace('.',',');
			
			return resultado;
		}

	}

	/**
	 * Agrega el MouseListener a los botones
	 */
	public void agregarMouseListener() {
		btn0.addMouseListener(this);
		btn1.addMouseListener(this);
		btn2.addMouseListener(this);
		btn3.addMouseListener(this);
		btn4.addMouseListener(this);
		btn5.addMouseListener(this);
		btn6.addMouseListener(this);
		btn7.addMouseListener(this);
		btn8.addMouseListener(this);
		btn9.addMouseListener(this);

		btnSuma.addMouseListener(this);
		btnResta.addMouseListener(this);
		btnMultiplicacion.addMouseListener(this);
		btnDivision.addMouseListener(this);
		btnResultado.addMouseListener(this);
		btn1x.addMouseListener(this);
		btnRaiz.addMouseListener(this);
		btnPorcentaje.addMouseListener(this);
		buttonComa.addMouseListener(this);
		
		btnMC.addMouseListener(this);
		btnMm.addMouseListener(this);
		btnMMas.addMouseListener(this);
		btnMMenos.addMouseListener(this);
		btnMR.addMouseListener(this);
		btnMS.addMouseListener(this);
		btnMultiplicacion.addMouseListener(this);

		btnC.addMouseListener(this);
		btnCE.addMouseListener(this);

		btnFlechaLeft.addMouseListener(this);
		btnMm.addMouseListener(this);

		rdbtnEstandar.addMouseListener(this);
		rdbtnCientfica.addMouseListener(this);

	}

	/**
	 * Método encargado de concatenar un string
	 * 
	 * @param dato valor a concatenar
	 * @return newValue el nuevo valor
	 */
	private String concatenarDato(String dato) {
		oldTemp = tfPantalla.getText();
		if (oldTemp.equals("0")) {
			oldTemp = "";
		}
		if (oldValue.equals("0")) {
			oldTemp = "";
			newValue = oldTemp + dato;
		} else {
			newValue = oldTemp + dato;
			oldTemp = newTemp;
		}
		newTemp = tfPantalla.getText();
		validarLongitud(newValue.length());
		return newValue;
	}

	/*
	 * Método Encargado de limpiar la pantalla completa
	 */
	public void limpiarC() {
		tfPantalla.setFont(standar);
		tfPantalla.setText("0");
		tfTemp.setText("");
		oldTemp = "";
		newTemp = "";
		opcion = "";
		operacion = "";
		pantallaArriba = "";
		newValue = "";
		oldValue = "";
		opcionTemporal = "";
		signo = "";
		contSuma = 0;
		contResta = 0;
		contMultiplicacion = 0;
		contDivision = 0;
		bloqueoFlecha = false;
		numero = false;
		bloqueo = false;
		reciproco = false;
		raiz = false;
	}

	/*
	 * Método Encargado de limpiar la parte que se tipea de la pantalla
	 */
	public void limpiarCE() {
		tfPantalla.setFont(standar);
		if(bloqueoFlecha) {
			tfPantalla.setText("0");
			tfTemp.setText("");
			oldTemp = "";
			newTemp = "";
			newValue = "";
			oldValue  = "";
			opcion = "";
			raizNumero = "";
			bloqueoFlecha = false;
			numero = false;
			bloqueo = false;
			reciproco = false;
			raiz = false;
		} else {
			tfPantalla.setText("0");
			oldTemp = "";
			newTemp = "";
			newValue = "";
			opcion = "";
			raizNumero = "";
			bloqueoFlecha = false;
			numero = false;
			bloqueo = false;
			reciproco = false;
			raiz = false;
		}
	}

	/**
	 * Verifica si la opcion precionada viene de un numero
	 * 
	 * @param opcion
	 * @return true, false
	 */
	public boolean numero(boolean opcion) {
		if (opcion == false) {
			numero = false;
		} else {
			numero = true;
		}

		return numero;
	}

	/**
	 * Setea el valor de la pantalla con el número previo
	 */
	public void regresaNumero() {
		if (numero == true) {
			if (tfPantalla.getText().length() < 2) {
				newValue = "0";
				tfPantalla.setText("0");
			} else if (tfPantalla.getText().length() > 1) {
				validarLongitud(tfPantalla.getText().length());
				tfPantalla.setText(tfPantalla.getText().substring(0, tfPantalla.getText().length() - 1));
			}
			newValue = tfPantalla.getText();
		}
	}

	/**
	 * Convierte lo que este en pantalla a su signo contrario (+/-)
	 */
	public void signoContrario() {
		double n = Double.parseDouble(tfPantalla.getText());
		double r = n * -1;
		if(r==-0) {
			r=0;
		}
		resultado = String.valueOf(r);
		resultado = resultado.replaceFirst("[.]0", "");
		newValue = resultado;
		tfPantalla.setText(newValue);
	}

	/**
	 * Devuelve el reciproco de un número
	 * 
	 * @param valor Número a calcular el recíproco
	 * @return msjError, Reciproco del número
	 */
	public String reciproco(String valor) {
		if (valor.equals("") || valor.equals("0")) {
			tfPantalla.setFont(smallFont);
			bloqueo = true;
			return "No se puede dividir entre cero";
		} else {
			double n1 = 1 / Double.parseDouble(valor);
			pantallaArriba = tfTemp.getText();
			pantallaArriba += " reciproc(" + valor + ") ";
			tfTemp.setText(pantallaArriba);
			reciproco = true;
			
			resultado = String.valueOf(n1);
			resultado = resultado.replaceFirst("[.]0", "");
			validarLongitud(resultado.length());
			newValue=resultado;
			
			return String.valueOf(newValue);
		}
	}
	
	/**
	 * Devuelve la raiz de un número
	 * @param valor número a calcular su raiz
	 * @return newValor raiz del número
	 */
	public String raiz(String valor) {
		double n1;
		if (valor.equals("")){
			valor=String.valueOf(Double.parseDouble("0"));
			resultado = String.valueOf(Math.sqrt(Double.parseDouble(valor)));
		}else {
			n1=Double.parseDouble(valor);
			resultado = String.valueOf(Math.sqrt(n1));
		}	
		pantallaArriba = tfTemp.getText();
		pantallaArriba += " sqrt(" + valor + ") ";
		pantallaArriba = pantallaArriba.replaceFirst("[.]0", "");
		tfTemp.setText(pantallaArriba);
		raiz = true;
		
		resultado = resultado.replaceFirst("[.]0", "");
		validarLongitud(resultado.length());
		newValue=resultado;
		
		return newValue;
	}
	
	/**
	 * Calcula el valor porcentual de un número
	 * @param vSolicitado valor a calcular el porcentaje
	 * @param vPorcentual valor porcentual a calcular
	 * @return porcentaje del número
	 */
	public String porcentaje(String vSolicitado, String vPorcentual){
		double porcentaje = 0;
		double numero = 0;
		double valor = 0;
		if ((vSolicitado.equals("") && vPorcentual.equals("")) || resultado.equals("0")) {
			resultado = String.valueOf(porcentaje);
		}else {
			porcentaje= Double.parseDouble(vPorcentual.replace(',','.'));
			numero = Double.parseDouble(vSolicitado.replace(',','.'));
			
			valor = ((numero * porcentaje) / 100); 
			resultado = String.valueOf(valor);
		}
		resultado = resultado.replaceFirst("[.]0", "");
		newValue=resultado;
		validarLongitud(resultado.length());
		return newValue;
	}
	
	/**
	 * Agrega la coma decimal al valor que este en la pantalla
	 */
	public void decimal() {
		isDecimal = true;
		String num = tfPantalla.getText();
		num= num + ",";
		newValue=num;
		tfPantalla.setText(num);
	}
	
	/**
	 * Almacena en memoria el número mostrado. 
	 */
	public void mStorage(String dato) {
		memory = dato.replace(',', '.');
		tfM.setText("M");
	}
	
	/**
	 * Recupera el número almacenado en memoria. El número permanece en memoria. 
	 */
	public void mRecall() {
		if(!oldValue.equals(memory)) {
			oldValue=tfPantalla.getText();
			tfPantalla.setText(memory);
			newValue  = memory;
			mActive = true;
		}else {
			if(memory.equals("")) {
				memory = "0";
			}
			tfPantalla.setText(memory);
			newValue  = memory;
			mActive = true;
		}
	
	}
	
	/**
	 * Suma el número mostrado a otro número que se encuentre en memoria pero no muestra la suma de estos números.
	 */
	public void mMas(String numero) {
		double n = Double.parseDouble(numero);
		double nMemory = Double.parseDouble(memory);
		memory = memory.replace(',', '.');
		memory = String.valueOf(nMemory+=n).replaceFirst("[.]0", "");
	}
	
	/**
	 * Resta el número mostrado a otro número que se encuentre en memoria pero no muestra la resta de estos números. 
	 */
	public void mMenos(String numero) {
		double n = Double.parseDouble(numero);
		double nMemory = Double.parseDouble(memory);
		memory = memory.replace(',', '.');
		memory = String.valueOf(nMemory-=n).replaceFirst("[.]0", "");
	}
	
	/**
	 * Elimina cualquier número almacenado en memoria.
	 */
	public void mClear() {
		memory = "";
		tfM.setText("");
	}
	
	/**
	 * Verifica el tamaño que tendra la pantalla
	 */
	public void validarLongitud(int longitud) {
		if (longitud > 15) {
			tfPantalla.setFont(smallFont);
		} else {
			tfPantalla.setFont(standar);
		}
	}

	public void agregarKeyListener() {
		btn1.addKeyListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void agregarMouseMoved() {
		btn1.addMouseMotionListener(this);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		/*
		 * if(e.getSource() == btn1) { btn1.setBackground(Color.RED); try {
		 * Thread.sleep(100); } catch (InterruptedException e1) { // TODO Auto-generated
		 * catch block e1.printStackTrace(); } btn1.setBackground(Color.YELLOW); }
		 */

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_1) {
			System.out.println("Escribio el 1");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Soltó una tecla");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Escribió una tecla");

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
