package com.javarush.test.level32.lesson15.big01;

import com.javarush.test.level32.lesson15.big01.listeners.FrameListener;
import com.javarush.test.level32.lesson15.big01.listeners.TabbedPaneChangeListener;
import com.javarush.test.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();       // панель с двумя вкладками
    private JTextPane htmlTextPane = new JTextPane();         // компонент для визуального редактирования html. Размещен на первой вкладке
    private JEditorPane plainTextPane = new JEditorPane();    // компонент для редактирования html в виде текста, отображает код html

    private UndoManager undoManager = new UndoManager();

    private UndoListener undoListener = new UndoListener(undoManager);

    public UndoListener getUndoListener() {
        return undoListener;
    }


    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }


    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(getContentPane(), "HTML Editor", "Newest version 1.0", JOptionPane.INFORMATION_MESSAGE);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.endsWith("Новый")) {
            controller.createNewDocument();
        } else if (command.endsWith("Открыть")) {
            controller.openDocument();
        } else if (command.endsWith("Сохранить")) {
            controller.saveDocument();
        } else if (command.endsWith("Сохранить как...")) {
            controller.saveDocumentAs();
        } else if (command.endsWith("Выход")) {
            controller.exit();
        } else if (command.endsWith("О программе")) {
            showAbout();
        }
    }


    public void initMenuBar()   // инициализация меню редактора
    {
        JMenuBar jMenuBar = new JMenuBar();      // панель меню
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);

        getContentPane().add(jMenuBar, BorderLayout.NORTH);
    }

    public void initEditor()     // инициализация панелей редактора
    {
        htmlTextPane.setContentType("text/html");

        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));
        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));
        tabbedPane.setPreferredSize(new Dimension(800, 600));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void initGui()       // инициализирует графический интерфейс
    {
        initMenuBar();
        initEditor();
        pack();    //  Causes this Window to be sized to fit the preferred size  and layouts of its subcomponents.
    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);

        addWindowListener(frameListener);
        this.setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    public void selectedTabChanged() { // Этот метод вызывается, когда произошла смена выбранной вкладки

        int tabIndex = tabbedPane.getSelectedIndex();

        if (tabIndex == 0) {
            controller.setPlainText(plainTextPane.getText());
        } else if (tabIndex == 1) {
            plainTextPane.setText(controller.getPlainText());
        }

        resetUndo();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {
        undoManager.undo();
    }

    public void redo() {
        undoManager.redo();

    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0;
    }
}
