package by.sfp.fe.ui;

import by.sfp.fe.domain.DomainCategory;
import by.sfp.fe.util.RestClient;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@SpringUI(path = "domainCategories")
@Theme("valo")
public class ServiceCategoriesPage extends UI {
    private VerticalLayout layout;
    private Optional<DomainCategory> selectedDomainCategory;

    @Autowired
    private RestClient client;

    @Override
    protected void init(VaadinRequest request) {
        setupLayout();
        addForm();
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        setContent(layout);
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setSpacing(true);
        formLayout.setWidth("80%");

        ComboBox<DomainCategory> select = new ComboBox<>("Выберите категорию сферы услуг");
        select.setItemCaptionGenerator(DomainCategory::getName);
        select.setItems(client.getDomainCategories());
        select.addSelectionListener(event -> selectedDomainCategory = event.getSelectedItem());

        TextField categoryName = new TextField();
        categoryName.setWidth("30%");

        Button addButton = new Button("");
        addButton.setIcon(VaadinIcons.PLUS);
        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        consumeToSendNewClassCategory(addButton, categoryName);

        formLayout.addComponents(select, categoryName, addButton);
        layout.addComponent(formLayout);
    }

    private void consumeToSendNewClassCategory(Button button, TextField textField) {
        button.addClickListener(e-> client.addNewClassCategory(selectedDomainCategory.get().getId(), textField.getValue()));
    }
}
