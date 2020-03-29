package M1.View;

import M1.Database;
import M1.Mensalidade;
import M1.Socio;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class GerarMensalidadeSocio extends JPanel {
    Database database = Database.getInstance();
    JLabel dataVencimento = new JLabel("Data Vencimento: ");
    JButton gerarMensalidade = new JButton("Gerar Mensalidade");
    JButton voltar = new JButton("Voltar");

    public JPanel getPanel(JPanel panels, AcaoSocio acaoSocio) {
        this.setLayout(new FlowLayout());
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        this.add(dataVencimento);
        this.add(datePicker);
        this.add(gerarMensalidade);
        this.add(voltar);

        gerarMensalidade.addActionListener(e -> {
            for (Socio socio : database.consultaSocio()) {
                Mensalidade mensalidade = new Mensalidade((Date) datePicker.getModel().getValue(), null, 150D, false, 0D, 0D);
                socio.registrarMensalidade(mensalidade);
            }
        });

        voltar.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            acaoSocio.atualizarLista();
            layout.show(panels, "0");
        });

        return this;
    }

    private static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "dd-MM-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
    }
}
