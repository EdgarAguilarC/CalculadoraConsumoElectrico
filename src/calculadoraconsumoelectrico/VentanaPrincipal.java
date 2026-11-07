  package calculadoraconsumoelectrico;

  import javax.swing.*;
  import javax.swing.table.DefaultTableModel;
  import java.awt.*;
  import java.util.ArrayList;

  public class VentanaPrincipal extends JFrame {

    private JTextField txtNombre;
    private JTextField txtPotencia;
    private JTextField txtHoras;

    private JTable tabla;
    private DefaultTableModel modelo;

    private JLabel lblTotal;

    private ArrayList<AparatoElectrico> aparatos;

    public VentanaPrincipal() {

        aparatos = GestorArchivos.cargar();

        setTitle("Calculadora de Consumo Eléctrico");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
        actualizarTabla();
    }

    private void crearInterfaz() {

        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel(
                "CALCULADORA DE CONSUMO ELÉCTRICO",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(
                new GridLayout(4, 2, 8, 8)
        );

        panelFormulario.setBorder(
                BorderFactory.createTitledBorder("Registrar aparato")
        );

        txtNombre = new JTextField();
        txtPotencia = new JTextField();
        txtHoras = new JTextField();

        JButton btnAgregar = new JButton("Agregar aparato");
        JButton btnEliminar = new JButton("Eliminar seleccionado");

        panelFormulario.add(new JLabel("Nombre del aparato:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Potencia (Watts):"));
        panelFormulario.add(txtPotencia);

        panelFormulario.add(new JLabel("Horas de uso diario:"));
        panelFormulario.add(txtHoras);

        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnEliminar);

        modelo = new DefaultTableModel(
                new String[]{
                    "Aparato",
                    "Watts",
                    "Horas",
                    "Consumo diario (kWh)",
                    "Consumo mensual (kWh)"
                }, 0
        );

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);

        JPanel centro = new JPanel(new BorderLayout(10, 10));

        centro.add(panelFormulario, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        lblTotal = new JLabel("Consumo total diario: 0.00 kWh");

        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));

        add(lblTotal, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregarAparato());

        btnEliminar.addActionListener(e -> eliminarAparato());
     }

     private void agregarAparato() {

        try {

            String nombre = txtNombre.getText().trim();

            double potencia = Double.parseDouble(
                    txtPotencia.getText()
            );

            double horas = Double.parseDouble(
                    txtHoras.getText()
            );

            if (nombre.isEmpty() || potencia <= 0
                    || horas <= 0 || horas > 24) {

                JOptionPane.showMessageDialog(
                        this,
                        "Verifica los datos ingresados."
                );

                return;
            }

            AparatoElectrico aparato =
                    new AparatoElectrico(nombre, potencia, horas);

            aparatos.add(aparato);

            GestorArchivos.guardar(aparatos);

            actualizarTabla();

            txtNombre.setText("");
            txtPotencia.setText("");
            txtHoras.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingresa valores numéricos válidos."
            );
         }
      }

      private void eliminarAparato() {

        int fila = tabla.getSelectedRow();

        if (fila >= 0) {

            aparatos.remove(fila);

            GestorArchivos.guardar(aparatos);

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un aparato de la tabla."
            );
        }
     }

      private void actualizarTabla() {

        modelo.setRowCount(0);

        for (AparatoElectrico aparato : aparatos) {

            double diario =
                    CalculadoraConsumo.calcularConsumoDiario(aparato);

            double mensual =
                    CalculadoraConsumo.calcularConsumoMensual(aparato);

            modelo.addRow(new Object[]{
                aparato.getNombre(),
                aparato.getPotenciaWatts(),
                aparato.getHorasUsoDiario(),
                String.format("%.2f", diario),
                String.format("%.2f", mensual)
            });
        }

        double total =
                CalculadoraConsumo.calcularConsumoTotal(
                        aparatos.toArray(new AparatoElectrico[0])
                );

        lblTotal.setText(
                String.format("Consumo total diario: %.2f kWh", total)
        );
      }
   } 
