package com.oxygenxml.sample;
import ro.sync.ecss.extensions.api.AuthorDocumentFilter;
import ro.sync.ecss.extensions.api.AuthorDocumentFilterBypass;

public class CharactersFilter extends AuthorDocumentFilter {
  @Override
  public void insertText(AuthorDocumentFilterBypass filterBypass, int offset, java.lang.String toInsert) {
    toInsert = toInsert.replaceAll("y", "z");
    toInsert = toInsert.replaceAll("q", "w");
    filterBypass.insertText(offset, toInsert);
  }
}
