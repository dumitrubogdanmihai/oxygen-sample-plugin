package com.oxygenxml.sample;

import java.net.URL;

import ro.sync.ecss.extensions.api.AuthorDocumentController;
import ro.sync.exml.plugin.workspace.WorkspaceAccessPluginExtension;
import ro.sync.exml.workspace.api.PluginWorkspace;
import ro.sync.exml.workspace.api.editor.WSEditor;
import ro.sync.exml.workspace.api.editor.page.WSEditorPage;
import ro.sync.exml.workspace.api.editor.page.author.WSAuthorEditorPage;
import ro.sync.exml.workspace.api.listeners.WSEditorChangeListener;
import ro.sync.exml.workspace.api.standalone.StandalonePluginWorkspace;

public class WorkspaceAccessImpl implements WorkspaceAccessPluginExtension {

  public void applicationStarted(final StandalonePluginWorkspace pluginWorkspaceAccess) {
    pluginWorkspaceAccess.addEditorChangeListener(new WSEditorChangeListener() {
      @Override
      public void editorOpened(URL editorLocation) {
        super.editorOpened(editorLocation);
        WSEditor editorAccess = pluginWorkspaceAccess.getEditorAccess(editorLocation, PluginWorkspace.MAIN_EDITING_AREA);
        WSEditorPage currentPage = editorAccess.getCurrentPage();
        if (currentPage instanceof WSAuthorEditorPage) {
          WSAuthorEditorPage authorEditorPage = (WSAuthorEditorPage) currentPage;
          AuthorDocumentController ctrl = authorEditorPage.getAuthorAccess().getDocumentController();
          ctrl.setDocumentFilter(new CharactersFilter());
        }
      }
    }, PluginWorkspace.MAIN_EDITING_AREA);
  }

  public boolean applicationClosing() {
    return false;
  }
}
