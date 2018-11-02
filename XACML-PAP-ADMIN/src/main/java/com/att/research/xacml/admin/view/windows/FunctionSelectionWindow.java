/*
 * 
 * Copyright (C) 2013-2018 AT&T Intellectual Property.
 *
 * SPDX-License-Identifier: MIT
 *
 */
package com.att.research.xacml.admin.view.windows;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.att.research.xacml.admin.XacmlAdminUI;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Buffered.SourceException;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class FunctionSelectionWindow extends Window {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button buttonSave;
	@AutoGenerated
	private Table tableFunctions;
	@AutoGenerated
	private TextField textFieldFilter;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log logger	= LogFactory.getLog(FunctionSelectionWindow.class);
	private final FunctionSelectionWindow self = this;
	private final String defaultFunctionID;
	private String selectedFunction = null;
	private boolean isSaved = false;
	private static SQLContainer highOrderFunctions = ((XacmlAdminUI) UI.getCurrent()).getHigherOrderBagContainer();
	/*
	 * Seems that when this view is created it may or may not be upper case.
	 */
	private static String PROPERTY_SHORTNAME = "shortname";
	private static String PROPERTY_XACMLID = "xacmlid";
	
	static {
		for (Object prop : FunctionSelectionWindow.highOrderFunctions.getContainerPropertyIds()) {
			logger.info("SQL Container Property Id: " + prop.toString());
			if (prop.toString().equalsIgnoreCase(PROPERTY_SHORTNAME)) {
				PROPERTY_SHORTNAME = prop.toString();
			} else if (prop.toString().equalsIgnoreCase(PROPERTY_XACMLID)) {
				PROPERTY_XACMLID = prop.toString();
			}
		}
		
	}
	
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public FunctionSelectionWindow(String defaultFunction) {
		buildMainLayout();
		//setCompositionRoot(mainLayout);
		setContent(mainLayout);
		//
		// Save
		//
		this.defaultFunctionID = defaultFunction;
		//
		// Close shortcut
		//
		this.setCloseShortcut(KeyCode.ESCAPE);
		//
		// Initialize GUI
		//
		this.initializeTextField();
		this.initializeFunctions();
		this.initializeButtons();
		//
		// Set our focus
		//
		this.tableFunctions.focus();
	}

	protected void initializeTextField() {
		//
		// Initialize GUI settings
		//
		this.textFieldFilter.setImmediate(true);
		//
		// Respond to the text change events
		//
		this.textFieldFilter.addTextChangeListener(new TextChangeListener() {
			private static final long serialVersionUID = 1L;
			SimpleStringFilter currentFilter = null;

			@Override
			public void textChange(TextChangeEvent event) {
				//
				// Remove current filter
				//
				if (this.currentFilter != null) {
					FunctionSelectionWindow.highOrderFunctions.removeContainerFilter(this.currentFilter);
					this.currentFilter = null;
				}
				//
				// Get the text
				//
				String value = event.getText();
				if (value != null && value.length() > 0) {
					//
					// Add the new filter
					//
					this.currentFilter = new SimpleStringFilter(PROPERTY_SHORTNAME, value, true, false);
					FunctionSelectionWindow.highOrderFunctions.addContainerFilter(this.currentFilter);
				}
			}
		});
	}
	protected void initializeFunctions() {
		//
		// Setup data source. Make sure there are no current filters
		//
		FunctionSelectionWindow.highOrderFunctions.removeAllContainerFilters();
		this.tableFunctions.setContainerDataSource(FunctionSelectionWindow.highOrderFunctions);
		//
		// Initialize GUI properties
		//
		this.tableFunctions.setImmediate(true);
		this.tableFunctions.setNullSelectionAllowed(false);
		this.tableFunctions.setRequired(true);
		this.tableFunctions.setRequiredError("Please select a function.");
		this.tableFunctions.setSelectable(true);
		this.tableFunctions.setPageLength(15);
		this.tableFunctions.setVisibleColumns(new Object[] {PROPERTY_SHORTNAME, PROPERTY_XACMLID});
		this.tableFunctions.setColumnHeaders(new String[] {"Short Function Name", "Xacml ID"});
		//
		// Respond to selection events
		//
		this.tableFunctions.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Object id = self.tableFunctions.getValue();
				if (id != null) {
					Item item = FunctionSelectionWindow.highOrderFunctions.getItem(id);
					if (item == null) {
						return;
					}
					Property<?> property = item.getItemProperty(PROPERTY_XACMLID);
					if (property == null) {
						return;
					}
					selectedFunction = property.getValue().toString();
					self.buttonSave.setEnabled(true);
				} else {
					self.buttonSave.setEnabled(false);
				}
			}			
		});
		//
		// Respond to double-click events
		//
		this.tableFunctions.addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					self.doSave();
				}
			}
		});
		//
		// Setup the default selection
		//
		this.buttonSave.setEnabled(false);
		if (this.defaultFunctionID != null) {
			for (Object id : FunctionSelectionWindow.highOrderFunctions.getItemIds()) {
				Item item = FunctionSelectionWindow.highOrderFunctions.getItem(id);
				if (item != null) {
					Property<?> property = item.getItemProperty(PROPERTY_XACMLID);
					if (property != null) {
						if (property.getValue().toString().equals(this.defaultFunctionID)) {
							this.tableFunctions.select(id);
							break;
						}
					}
				}
			}
		}
	}

	protected void initializeButtons() {
		this.buttonSave.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				self.doSave();
			}		
		});
	}
	
	protected void doSave() {
		try {
			//
			// Commit changes
			//
			self.tableFunctions.commit();
			//
			// We are saved
			//
			self.isSaved = true;
			//
			// Close the window
			//
			self.close();
		} catch (SourceException | InvalidValueException e) {
			//
			// Nothing to do, Vaadin highlights
			//
		}
	}
	
	public boolean isSaved() {
		return this.isSaved;
	}
	
	public String getSelectedFunction() {
		return this.selectedFunction;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// textFieldFilter
		textFieldFilter = new TextField();
		textFieldFilter.setCaption("Filter");
		textFieldFilter.setImmediate(false);
		textFieldFilter.setWidth("-1px");
		textFieldFilter.setHeight("-1px");
		mainLayout.addComponent(textFieldFilter);
		
		// tableFunctions
		tableFunctions = new Table();
		tableFunctions.setImmediate(false);
		tableFunctions.setDescription("Functions To Select From");
		tableFunctions.setWidth("100.0%");
		tableFunctions.setHeight("-1px");
		mainLayout.addComponent(tableFunctions);
		mainLayout.setExpandRatio(tableFunctions, 1.0f);
		
		// buttonSave
		buttonSave = new Button();
		buttonSave.setCaption("Save");
		buttonSave.setImmediate(true);
		buttonSave.setWidth("-1px");
		buttonSave.setHeight("-1px");
		mainLayout.addComponent(buttonSave);
		mainLayout.setComponentAlignment(buttonSave, new Alignment(48));
		
		return mainLayout;
	}

}
