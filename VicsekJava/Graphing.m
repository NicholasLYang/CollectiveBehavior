
X = zeros(1000, 500);
Y = zeros(1000, 500);
U = zeros(1000, 500);
V = zeros(1000, 500);

for i = 1:100

   xfile = strcat('x', num2str(i), '.txt'); 
   yfile = strcat('y', num2str(i), '.txt');
   ufile = strcat('u', num2str(i), '.txt');
   vfile = strcat('v', num2str(i), '.txt');
   X(i, :) = importdata(xfile, ' ');
   Y(i, :) = importdata(yfile, ' ');
   U(i, :) = importdata(ufile, ' ');
   V(i, :) = importdata(vfile, ' ');
   fprintf('%d\n', i);
end


        
for j = 1:500
  
    quiver(X(1:100, j), Y(1:100, j), U(1:100, j), V(1:100, j), 0.25);
    axis([-200,200,-200,200]);
    hold on
    Circle(0, 0, 110);
    hold off
    fprintf('%d\n', j);
    pause(0.5);
end



   