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

package jumpvm.code.mama;

import jumpvm.ast.mama.MaMaAstNode;
import jumpvm.exception.ExecutionException;
import jumpvm.memory.Heap;
import jumpvm.memory.Stack;
import jumpvm.memory.objects.ConsObject;
import jumpvm.memory.objects.MemoryObject;
import jumpvm.memory.objects.PointerObject;
import jumpvm.memory.objects.PointerObject.Type;
import jumpvm.vm.MaMa;

/**
 * Returns the tail of a nonempty list.
 * 
 * <pre>
 * if HP[ST[SP]].tag = CONS then
 *     ST[SP] := HP[ST[SP]].tl;
 * else 
 *     error;
 * fi
 * </pre>
 */
public class TlInstruction extends MaMaInstruction {
    /**
     * Create a new TlInstruction.
     * 
     * @param sourceNode AstNode that is responsible for this instruction's creation
     */
    public TlInstruction(final MaMaAstNode sourceNode) {
        super(sourceNode);
    }

    @Override
    public final void execute(final MaMa vm) throws ExecutionException {
        final Stack st = vm.getStack();
        final Heap hp = vm.getHeap();
        final MemoryObject object = hp.getElementAt(st.pop());
        if (object instanceof ConsObject) {
            st.push(new PointerObject(((ConsObject) object).getTl(), Type.POINTER_HEAP, "→tl", "Reference to tail"));
        } else {
            throw new ExecutionException(this, "not cons value");
        }
    }

    @Override
    public final String getDisplayHoverText() {
        return "Returns the tail of a nonempty list";
    }

    @Override
    public final String getMnemonic() {
        return "tl";
    }

    @Override
    public final String getParameter() {
        return null;
    }
}
