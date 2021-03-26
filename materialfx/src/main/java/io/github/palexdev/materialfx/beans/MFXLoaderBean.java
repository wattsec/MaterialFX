/*
 *     Copyright (C) 2021 Parisi Alessandro
 *     This file is part of MaterialFX (https://github.com/palexdev/MaterialFX).
 *
 *     MaterialFX is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     MaterialFX is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with MaterialFX.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.palexdev.materialfx.beans;

import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.util.Callback;

import java.net.URL;

/**
 * Support bean for {@code MFXHLoader} and {@code MFXVLoader}
 * Basically a wrapper for a {@code Node} which is the root of an fxml file,
 * the controller factory of the fxml file, the toggle button associated with the item
 * which is responsible for the views switching, the {@code URL} of the fxml file,
 * and an index which represents the toggle button position in the children list of the loader.
 */
public class MFXLoaderBean {
    //================================================================================
    // Properties
    //================================================================================
    private Node root;
    private final boolean defaultRoot;
    private final Callback<Class<?>, Object> controllerFactory;
    private final ToggleButton button;
    private final URL fxmlURL;

    //================================================================================
    // Constructors
    //================================================================================
    public MFXLoaderBean(ToggleButton button, URL fxmlURL, boolean defaultRoot) {
        this(button, fxmlURL, defaultRoot, null);
    }

    public MFXLoaderBean(ToggleButton button, URL fxmlURL, boolean defaultRoot, Callback<Class<?>, Object> controllerFactory) {
        this.button = button;
        this.fxmlURL = fxmlURL;
        this.defaultRoot = defaultRoot;
        this.controllerFactory = controllerFactory;
    }

    //================================================================================
    // Methods
    //================================================================================
    public Node getRoot() {
        return root;
    }

    public boolean isDefault() {
        return defaultRoot;
    }

    public Callback<Class<?>, Object> getControllerFactory() {
        return controllerFactory;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public ToggleButton getButton() {
        return button;
    }

    public URL getFxmlURL() {
        return fxmlURL;
    }

    public static class Builder {
        private final URL fxmlURL;
        private final ToggleButton button;
        private boolean defaultRoot;
        private Callback<Class<?>, Object> controllerFactory;

        private Builder(ToggleButton button, URL fxmlURL) {
            this.fxmlURL = fxmlURL;
            this.button = button;
        }

        public static Builder build(ToggleButton button, URL fxmlURL) {
            return new Builder(button, fxmlURL);
        }

        public Builder setDefaultRoot(boolean defaultRoot) {
            this.defaultRoot = defaultRoot;
            return this;
        }

        public Builder setControllerFactory(Callback<Class<?>, Object> controllerFactory) {
            this.controllerFactory = controllerFactory;
            return this;
        }

        public MFXLoaderBean get() {
            return new MFXLoaderBean(button, fxmlURL, defaultRoot, controllerFactory);
        }
    }
}