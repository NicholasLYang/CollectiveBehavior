
function [milling, polarization, cohesion] = Graph(agents, agentSize, sizeOfGraph, repulsionConst, frames, speed, fps, eta, alpha, lambda, gamma)
% Gives a total number of agents for the for loop
numOfAgents = size(agents);
% Graphs the agents using a quiver plot (adds arrows instead of dots)

polarization = zeros(frames);
milling = zeros(frames);
cohesion = zeros(frames);
o = zeros(numOfAgents(2));
for j = 1:frames

   p = [0 0];  
   m = [0 0 0];
   c = 0;
   % loops through the entire array, changing coordinates and direction
   for x = 1:numOfAgents(2)
       angle = agents(3, x);
       coords = [agents(1,x) agents(2,x)];
       direction = [cos(angle) sin(angle)];
       
       sumOfDirections = 0;
       numOfNeighbors = 0;
       f = [0 0];
       % first change direction
       % need to loop through array, find which agents are "neighbors"
       for y = 1:numOfAgents(2)
           nCor = [agents(1, y) agents(2, y)];
    % r is a scalar factor of the repulsion between an agent and its neighbor.
    % e is the vector pointing from the neighbor to the agent
    % Thus, r * e is the repulsion vector. This is added to f. 
    % f is the total sum of all the repulsion vectors.
          f1 = nCor - coords;
    % Remember, f1 is a vectior pointing from the agent to the neighbor

          
           distance = norm(f1);
          if distance < sizeOfGraph
           r = (1 + exp(norm(f1)/(sizeOfGraph * 0.1) - 2))^-1;
           e = (-f1)/norm(f1); 
           if f1 == 0
             e = [0, 0];
           end
           f = f + r * e; 
          end
           if distance < sizeOfGraph/2
               
               nAngle = agents(3, y);
               
                   

               % Basic Vicsek:
               cosine = dot(direction, f1)/(norm(f1) * norm(direction));
               if f1 == 0
                  cosine = 0;
               end
               sumOfDirections = sumOfDirections + nAngle * (1 + cosine) ;
               numOfNeighbors = numOfNeighbors + 1;

               
               %{
               More complex calculations 
               velocity = speed * [cos(agents(3, x)), sin(agents(3, x))];
               omega = velocityConst * velocity * sin(agents(3, y) - agents(3,x)) + distanceConst * distance * 1 - (dot((f1-f2), f1)/(norm(f1) * norm(f2)))^2; 
               %}
           end
       end
       randNum = rand();
       if randNum < lambda
           r = 0;
       end
           r = randn();
     
       o(x) = (1 - alpha) * o(x) + randn() + gamma * r;
     

      % Add on repulsion constant
       f = f * repulsionConst;
      % This is extrinsic noise, noise which comes from "free will"
      % agents(3,x) = mod(sumOfDirections / numOfNeighbors + rand() * pi * (1 - 2 * rand()), 2 * pi); 
      % This is intrinsic noise 
       angle = mod(eta * o(x) + sumOfDirections / numOfNeighbors, 2 * pi);

       % Chate noise
      % agents(3,x) = mod( sumOfDirections / numOfNeighbors + 2 * pi * eta * real(exp(1i * rand())), 2 * pi);
       
      % This is no noise
      % agents(3,x) = mod(sumOfDirections/numOfNeighbors, 2 * pi);
      % agents(1, x) = mod(speed * cos(agents(3, x)) + agents(1, x), sizeOfGraph);
      % agents(2, x) = mod(speed * sin(agents(3, x)) + agents(2, x), sizeOfGraph);
     % With repulsion
     agents(1, x) = mod(speed * cos(agents(3,x)) + f(1) + agents(1, x), sizeOfGraph);
     agents(2, x) = mod(speed * sin(agents(3,x)) + f(2) + agents(2, x), sizeOfGraph);
     agents(3, x) = angle;
     direction = [cos(angle) sin(angle)];
     % fprintf('Agent has a repulsion of %d\n', f(1));
     % More advanced noise
 
     % fprintf('Agent %d has xcor of %d, a ycor of %d and a direction of %d\n', x, agents(1, x), agents(2, x), agents(3,x)); 
    
     p = p + direction;
     m = m + cross([coords 0], [direction 0])/(norm(coords) * speed);
     c = c + exp(-norm(coords)/(2 * sizeOfGraph));
   end
 
    polarization(j) = norm(p) / numOfAgents(2);
    milling(j) = norm(m)/ numOfAgents(2);    
    cohesion(j) = c / numOfAgents(2); 
     quiver(agents(1,:), agents(2, :), cos(agents(3,:)), sin(agents(3,:)), agentSize);
     axis([0,sizeOfGraph,0,sizeOfGraph]);
    %{
    fprintf('Polarization is %d\n', polarization(j));
    fprintf('Milling is %d\n', milling(j));
    fprintf('Cohesion is %d\n', cohesion(j))
    %}
    fprintf('%d\n',j);
    pause(1/fps)
    drawnow
end

%plot(polarization)
%axis([1,frames,0,2]);





