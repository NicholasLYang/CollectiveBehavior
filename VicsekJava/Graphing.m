s = 500; 
n = 1000;
scale = 0.5;
X = zeros(n, 500);
Y = zeros(n, 500);
U = zeros(n, 500);
V = zeros(n, 500);
open(writerObj);
for i = 1:n

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
  
    quiver(X(1:n, j), Y(1:n, j), scale * U(1:n, j), scale * V(1:n, j), 'AutoScale','off');
    axis equal 
    axis([-600,600,-600,600 ]);
    hold on
    Circle(0, 0, 550);
    hold off
    F(j) = getframe;
    fprintf('%d\n', j);
end

movie2avi(F, 'Vicsek.avi')


   