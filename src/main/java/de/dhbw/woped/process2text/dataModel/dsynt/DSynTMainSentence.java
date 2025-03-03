/* (C)2022 */
package de.dhbw.woped.process2text.dataModel.dsynt;

import de.dhbw.woped.process2text.dataModel.intermediate.ExecutableFragment;
import de.dhbw.woped.process2text.textPlanning.IntermediateToDSynTConverter;
import java.util.ArrayList;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DSynTMainSentence extends DSynTSentence {
  private Element verb;
  private Element object;
  private Element role;

  public DSynTMainSentence(ExecutableFragment eFrag) {
    this.eFrag = eFrag;
    createDSynTRepresentation();
  }

  public void createDSynTRepresentation() {

    // Create document
    doc = new DocumentImpl();
    Element root = doc.createElement("dsynts");
    doc.appendChild(root);

    // Create verb
    verb =
        IntermediateToDSynTConverter.createVerb(
            doc, eFrag, IntermediateToDSynTConverter.VERB_TYPE_MAIN);
    root.appendChild(verb);

    // Create business object
    if (eFrag.hasBO()) {
      object = IntermediateToDSynTConverter.createBO(doc, eFrag);
      verb.appendChild(object);
    }

    // Create role
    if (!eFrag.getRole().equals("")) {
      role = IntermediateToDSynTConverter.createRole(doc, eFrag);
      verb.appendChild(role);
    }

    // Create addition
    if (!eFrag.getAddition().equals("")) {
      IntermediateToDSynTConverter.createAddition(doc, verb, eFrag);
    }

    // Create mods
    if (eFrag.getAllMods() != null) {
      IntermediateToDSynTConverter.appendMods(doc, eFrag, verb, object);
    }

    // create additional sentences
    if (eFrag.getSentencList().size() > 0) {
      IntermediateToDSynTConverter.createAddSentences(doc, verb, eFrag);
    }
  }

  public void changeRole() {
    // Create role
    if (!eFrag.getRole().equals("")) {
      verb.removeChild(role);
      role = IntermediateToDSynTConverter.createRole(doc, eFrag);
      verb.appendChild(role);
    }
  }

  public void addCoordSentences(ArrayList<DSynTMainSentence> sentences) {
    if (sentences.size() == 1) {

      Element coord = doc.createElement("dsyntnode");
      coord.setAttribute("class", "coordinating_conj");
      coord.setAttribute("rel", "COORD");
      coord.setAttribute("lexeme", "AND");
      verb.appendChild(coord);

      Element cVerb =
          IntermediateToDSynTConverter.createVerb(
              doc,
              sentences.get(0).getExecutableFragment(),
              IntermediateToDSynTConverter.VERB_TYPE_SUBCONDITION);
      coord.appendChild(cVerb);

      Element cObject =
          IntermediateToDSynTConverter.createBO(doc, sentences.get(0).getExecutableFragment());
      cVerb.appendChild(cObject);
    }
  }

  public Element getVerb() {
    return verb;
  }

  public Document getDSynT() {
    return doc;
  }

  public ExecutableFragment getExecutableFragment() {
    return eFrag;
  }
}
