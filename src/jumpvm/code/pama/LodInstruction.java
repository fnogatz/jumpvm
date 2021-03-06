/*
 * JumpVM: The Java Unified Multi Paradigm Virtual Machine.
 * Copyright (C) 2013 Tim Wiederhake
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package jumpvm.code.pama;

import jumpvm.ast.pama.PaMaAstNode;
import jumpvm.exception.ExecutionException;
import jumpvm.memory.objects.StackObject;
import jumpvm.vm.PaMa;

/**
 * Load Object Data.
 *
 * <pre>
 * SP := SP + 1;
 * STORE[SP] := STORE[base(p, MP) + q];
 * </pre>
 */
public class LodInstruction extends PaMaInstruction {
    /** Depth difference. */
    private final int p;

    /** Relative address. */
    private final int q;

    /** Object name. */
    private final String identifier;

    /**
     * Create a new LodInstruction.
     *
     * @param sourceNode AstNode that is responsible for this instruction's creation
     * @param p depth difference
     * @param q relative address
     * @param identifier object name
     */
    public LodInstruction(final PaMaAstNode sourceNode, final int p, final int q, final String identifier) {
        super(sourceNode);
        this.p = p;
        this.q = q;
        this.identifier = identifier;
    }

    @Override
    public final void execute(final PaMa vm) throws ExecutionException {
        final StackObject object = vm.getElementAt(base(vm, p, vm.getMarkPointer().getValue()) + q);
        vm.push(object);
    }

    @Override
    public final String getDisplayHoverText() {
        return "Load Object Data " + identifier;
    }

    @Override
    public final String getMnemonic() {
        return "lod";
    }

    @Override
    public final String getParameter() {
        return String.valueOf(p) + " " + String.valueOf(q);
    }
}
