package Pessoa;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Form3 extends JFrame implements ActionListener {
    private JTextField campoNome, campoIdade;
    private JRadioButton radioMasculino, radioFeminino;
    private JLabel labelNumero;
    private List<Pessoa> pessoas;
    private ButtonGroup grupoSexo;

    public Form3() {
        setTitle("Versao 3.0");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        pessoas = new ArrayList<>();

        JPanel painelCampos = new JPanel(new GridLayout(4,
                2));

        painelCampos.add(new JLabel("Numero:"));
        labelNumero = new JLabel(String.valueOf(Pessoa.getKp()));
        painelCampos.add(labelNumero);

        painelCampos.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painelCampos.add(campoNome);
        
        painelCampos.add(new JLabel("Sexo:"));
        radioMasculino = new JRadioButton("M");
        radioFeminino = new JRadioButton("F");
        grupoSexo = new ButtonGroup();
        grupoSexo.add(radioMasculino);
        grupoSexo.add(radioFeminino);
        JPanel sexoPanel = new JPanel(); 
        sexoPanel.add(radioMasculino);
        sexoPanel.add(radioFeminino);
        painelCampos.add(sexoPanel); 

        painelCampos.add(new JLabel("Idade:"));
        campoIdade = new JTextField();
        painelCampos.add(campoIdade);

        add(painelCampos, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(this);
        painelBotoes.add(btnOk);

        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.addActionListener(this);
        painelBotoes.add(btnMostrar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(this);
        painelBotoes.add(btnLimpar);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(this);
        painelBotoes.add(btnSair);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("OK")) {
            String nome = campoNome.getText();

            String sexoStr = "";
            if (radioMasculino.isSelected()) {
                sexoStr = "M";
            } else if (radioFeminino.isSelected()) {
                sexoStr = "F";
            }

            String idadeStr = campoIdade.getText();

            if (nome.isEmpty() || sexoStr.isEmpty() || idadeStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos sao obrigatorios!");
                return;
            }

            try {
                int idade = Integer.parseInt(idadeStr);

                char sexo = sexoStr.charAt(0);

                Pessoa novaPessoa = new Pessoa(nome, sexo, idade);
                pessoas.add(novaPessoa);

                labelNumero.setText(String.valueOf(Pessoa.getKp()));

                JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Idade deve ser um numero valido.");
            }
        } else if (comando.equals("Mostrar")) {
            if (pessoas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma pessoa cadastrada.");
            } else {
                StringBuilder dados = new StringBuilder("Pessoas cadastradas:\n");
                for (Pessoa pessoa : pessoas) {
                    dados.append("Nome: ").append(pessoa.getNome())
                            .append(", Sexo: ").append(pessoa.getSexo())
                            .append(", Idade: ").append(pessoa.getIdade())
                            .append("\n");
                }
                dados.append("\nTotal de pessoas: ").append(Pessoa.getKp());
                JOptionPane.showMessageDialog(this, dados.toString());
            }
        } else if (comando.equals("Limpar")) {
            campoNome.setText("");
            grupoSexo.clearSelection();
            campoIdade.setText("");
            JOptionPane.showMessageDialog(this, "Campos limpos!");
        } else if (comando.equals("Sair")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Form3 form = new Form3();
        form.setVisible(true);
    }
}