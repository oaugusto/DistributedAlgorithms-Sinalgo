/*
 Copyright (c) 2007, Distributed Computing Group (DCG)
                    ETH Zurich
                    Switzerland
                    dcg.ethz.ch

 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 - Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.

 - Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the
   distribution.

 - Neither the name 'Sinalgo' nor the names of its contributors may be
   used to endorse or promote products derived from this software
   without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package projects.pa3;

import projects.pa3.nodes.nodeImplementations.NodeThree;
import sinalgo.nodes.Node;
import sinalgo.runtime.AbstractCustomGlobal;
import sinalgo.tools.Tools;
import sinalgo.tools.statistics.UniformDistribution;


public class CustomGlobal extends AbstractCustomGlobal{
	
	/* (non-Javadoc)
	 * @see runtime.AbstractCustomGlobal#hasTerminated()
	 */
	public boolean hasTerminated() {
		for (Node e : Tools.getNodeList()) {
			NodeThree node = (NodeThree)e;
			if (!node.stopSimulation) {
				return false;
			}
		}
		return true;
	}
	
	@AbstractCustomGlobal.CustomButton(buttonText="Clear Graph", toolTipText="Clear a graph")
	public void sampleButton2() {
		for (Node e : Tools.getNodeList()){
			e.outgoingConnections.removeAndFreeAllEdges();
		}
		Tools.repaintGUI();
	}
	
	@AbstractCustomGlobal.CustomButton(buttonText="Build Graph", toolTipText="Builds a graph")
	public void sampleButton1() {
		build();
		Tools.repaintGUI();
	}
	
	public void build() {
		for (int i = 1; i <= Tools.getNodeList().size(); i++) {
			for (int j = i + 1; j <= Tools.getNodeList().size(); j++) {
				if (UniformDistribution.nextUniform(0, 1) < 0.2) {
					Tools.getNodeByID(i).addConnectionTo(Tools.getNodeByID(j));
				}
			}
		}
			
	}
}
