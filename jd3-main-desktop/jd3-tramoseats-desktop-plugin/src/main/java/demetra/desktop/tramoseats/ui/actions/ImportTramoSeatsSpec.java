/*
 * Copyright 2016 National Bank of Belgium
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved 
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and 
 * limitations under the Licence.
 */
package demetra.desktop.tramoseats.ui.actions;

import demetra.desktop.Config;
import demetra.desktop.interchange.Importable;
import demetra.desktop.interchange.InterchangeManager;
import demetra.desktop.nodes.SingleNodeAction;
import demetra.desktop.tramoseats.documents.TramoSeatsSpecManager;
import demetra.desktop.workspace.WorkspaceFactory;
import demetra.desktop.workspace.WorkspaceItem;
import demetra.information.InformationSet;
import demetra.toolkit.io.xml.information.XmlInformationSet;
import demetra.tramoseats.TramoSeatsSpec;
import demetra.tramoseats.io.information.TramoSeatsSpecMapping;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;
import javax.swing.JMenuItem;
import nbbrd.io.text.Parser;
import nbbrd.io.xml.bind.Jaxb;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.nodes.Node;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.Presenter;

/**
 * Action on Tramo specification workspace node allowing the import
 *
 * @author Mats Maggi
 */
@ActionID(category = "Edit", id = "demetra.desktop.tramoseats.ui.actions.ImportTramoSeatsSpec")
@ActionRegistration(displayName = "#CTL_ImportTramoSeatsSpec", lazy = false)
@ActionReferences({
    @ActionReference(path = TramoSeatsSpecManager.PATH, position = 1000)
})
@Messages("CTL_ImportTramoSeatsSpec=Import from")
public class ImportTramoSeatsSpec extends SingleNodeAction<Node> implements Presenter.Popup {

    public ImportTramoSeatsSpec() {
        super(Node.class);
    }

    @Override
    protected void performAction(Node activatedNode) {

    }

    @Override
    public JMenuItem getPopupPresenter() {
        JMenuItem result = InterchangeManager.get().newImportMenu(getImportables());
        result.setText(Bundle.CTL_ImportTramoSeatsSpec());
        return result;
    }

    @Override
    protected boolean enable(Node activatedNode) {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }

    private List<Importable> getImportables() {
        return Collections.singletonList(new Importable() {

            @Override
            public String getDomain() {
                return TramoSeatsSpec.class.getName();
            }

            @Override
            public void importConfig(Config config) throws IllegalArgumentException {
                TramoSeatsSpec spec = fromConfig(config);
                if (spec != null) {
                    WorkspaceItem<TramoSeatsSpec> ndoc = WorkspaceItem.newItem(TramoSeatsSpecManager.ID, config.getName(), spec);
                    WorkspaceFactory.getInstance().getActiveWorkspace().add(ndoc);
                }
            }
        });
    }

    private static TramoSeatsSpec fromConfig(@NonNull Config config) throws IllegalArgumentException {
        if (!TramoSeatsSpec.class.getName().equals(config.getDomain())) {
            throw new IllegalArgumentException("Invalid config");
        }
        return Optional.ofNullable(config.getParameter("specification"))
                .map(INFORMATIONPARSER::parse)
                .map(TramoSeatsSpecMapping.SERIALIZER_V3::read)
                .orElse(null);
    }

    private static final Parser<InformationSet> INFORMATIONPARSER = Jaxb.Parser.of(XmlInformationSet.class).asParser().andThen(XmlInformationSet::create);
}
